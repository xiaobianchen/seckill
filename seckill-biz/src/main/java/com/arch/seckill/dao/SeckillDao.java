package com.arch.seckill.dao;

import com.arch.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao extends BasicDao<Seckill> {

    List<Seckill> queryAll();

    Seckill findBySecKillId(long seckillId);

    int reduceNumber(long seckillId,Date currentTime);
}