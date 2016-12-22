package com.arch.seckill.dao;

import com.arch.seckill.entity.Successkill ;

public interface SuccesskillDao extends BasicDao<Successkill> {

    int insertSuccessKill(long seckillId,long phone);

    Successkill queryByIdWithSeckill(long seckillId,long phone);
    
}