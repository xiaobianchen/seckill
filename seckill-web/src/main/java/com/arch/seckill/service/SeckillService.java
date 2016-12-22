package com.arch.seckill.service;

import com.arch.seckill.dto.Exposer;
import com.arch.seckill.dto.SeckillDto;
import com.arch.seckill.dto.SeckillExcution;

import java.util.List;

/**
 * Created by chenxiaobian on 16/12/22.
 */
public interface SeckillService {

    List<SeckillDto> queryAll();

    SeckillDto findById(Long seckillId);

    Exposer exposeSeckillUrl(long seckillId);

    SeckillExcution executeSeckill(long seckillId,long phone,String md5);
}
