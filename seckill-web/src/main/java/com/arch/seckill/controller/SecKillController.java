package com.arch.seckill.controller;

import com.arch.seckill.dto.Exposer;
import com.arch.seckill.dto.SeckillDto;
import com.arch.seckill.dto.SeckillExcution;
import com.arch.seckill.dto.SeckillResult;
import com.arch.seckill.enums.SecKillEnum;
import com.arch.seckill.exception.RepeatKillException;
import com.arch.seckill.exception.SeckillCloseException;
import com.arch.seckill.exception.SeckillException;
import com.arch.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by chenxiaobian on 16/12/22.
 */
@Controller
@RequestMapping("/seckill")
public class SecKillController {

    @Autowired
    private SeckillService seckillService;

    /**
     * 返回秒杀商品列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<SeckillDto> seckills = seckillService.queryAll();
        model.addAttribute("seckills", seckills);
        return "list";
    }

    /**
     * 返回秒杀商品详情页
     *
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String getSecKillProductDetails(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            throw new SeckillException("秒杀商品不存在");
        }

        SeckillDto seckillDto = seckillService.findById(seckillId);
        if (seckillDto == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckillDto);
        return "detail";
    }

    /**
     * 暴露秒杀地址
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})//http context
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone", required = false) Long phone) {
        if (phone == null) {
            return new SeckillResult<SeckillExcution>(false, "手机号码为空!");
        }
        try {
            SeckillExcution seckillExcution = seckillService.executeSeckill(seckillId, phone, md5);
            return new SeckillResult<SeckillExcution>(true, seckillExcution);
        } catch (RepeatKillException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SecKillEnum.REPEAT_KILL.getDesc());
            return new SeckillResult<SeckillExcution>(true, seckillExcution);
        } catch (SeckillCloseException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SecKillEnum.END.getDesc());
            return new SeckillResult<SeckillExcution>(true, seckillExcution);
        } catch (Exception e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SecKillEnum.INNER_ERROR.getDesc());
            return new SeckillResult<SeckillExcution>(true, seckillExcution);
        }
    }

    /**
     * 返回当前系统时间
     *
     * @return
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time() {
        Date date = new Date();
        return new SeckillResult<Long>(true, date.getTime());
    }


}
