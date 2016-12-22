package com.arch.seckill.service.impl;

import com.arch.seckill.dao.SeckillDao;
import com.arch.seckill.dao.SuccesskillDao;
import com.arch.seckill.dto.Exposer;
import com.arch.seckill.dto.SeckillDto;
import com.arch.seckill.dto.SeckillExcution;
import com.arch.seckill.entity.Seckill;
import com.arch.seckill.entity.Successkill;
import com.arch.seckill.enums.SecKillEnum;
import com.arch.seckill.exception.RepeatKillException;
import com.arch.seckill.exception.SeckillCloseException;
import com.arch.seckill.exception.SeckillException;
import com.arch.seckill.framework.BeanMappingService;
import com.arch.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chenxiaobian on 16/12/22.
 */
@Component
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccesskillDao successkillDao;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<SeckillDto> queryAll() {
        List<Seckill> seckillList = seckillDao.queryAll();
        return seckillList.stream().map(Seckill -> beanMappingService.transform(Seckill, SeckillDto.class)).collect(Collectors.toList());
    }

    @Override
    public SeckillDto findById(Long seckillId) {
        Seckill seckill = seckillDao.findBySecKillId(seckillId);
        return beanMappingService.transform(seckill, SeckillDto.class);
    }

    @Override
    public Exposer exposeSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.findBySecKillId(seckillId);
        Date createTime = seckill.getCreateTime();
        Date endTime = seckill.getEndTime();
        Date currentTime = new Date();

        //秒杀成功
        if (currentTime.after(createTime) && currentTime.before(endTime)) {
            String md5 = getMd5(seckillId);
            return new Exposer(true, md5, seckillId);
        } else {
            return new Exposer(false, seckillId, currentTime.getTime(), createTime.getTime(), endTime.getTime());
        }
    }

    @Override
    public SeckillExcution executeSeckill(long seckillId, long phone, String md5) {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException(SecKillEnum.DATA_REWRITE.getDesc());
        }
        Date currentTime = new Date();
        try {
            int insertCount = successkillDao.insertSuccessKill(seckillId, phone);
            if (insertCount <= 0) {
                throw new RepeatKillException(SecKillEnum.REPEAT_KILL.getDesc());
            } else {
                //热点商品竞争
                int updateCount = seckillDao.reduceNumber(seckillId, currentTime);

                //没有更新记录
                if (updateCount <= 0) {
                    throw new SeckillCloseException(SecKillEnum.END.getDesc());
                } else {
                    //秒杀成功
                    Successkill successKill = successkillDao.queryByIdWithSeckill(seckillId, phone);
                    return new SeckillExcution(seckillId, SecKillEnum.SUCCESS.getCode(), successKill);
                }
            }

        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            //rollback
            throw new SeckillException("服务器内部错误:" + e.getMessage());
        }
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + "dfsdfdsf7JHJJ";
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
