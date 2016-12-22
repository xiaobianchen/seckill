package com.arch.seckill.dao;

import com.arch.seckill.entity.Seckill;

import java.util.List;

public interface SeckillDao extends BasicDao<Seckill> {

    List<Seckill> queryAll();
}