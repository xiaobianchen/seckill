package com.arch.seckill.service.impl;

import com.arch.seckill.dao.SeckillDao;
import com.arch.seckill.dto.SeckillDto;
import com.arch.seckill.entity.Seckill;
import com.arch.seckill.framework.BeanMappingService;
import com.arch.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private BeanMappingService beanMappingService;

    @Override
    public List<SeckillDto> queryAll() {
        List<Seckill> seckillList = seckillDao.queryAll();
        return seckillList.stream().map(Seckill->beanMappingService.transform(Seckill,SeckillDto.class)).collect(Collectors.toList());
    }
}
