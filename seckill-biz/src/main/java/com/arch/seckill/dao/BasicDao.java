package com.arch.seckill.dao;


import com.arch.seckill.entity.BasicEntity;


public interface BasicDao<T extends BasicEntity> {
    public T findById(Integer id);

    public int create(T entity);

    public void update(T entity);

    public void delete(T entity);
}