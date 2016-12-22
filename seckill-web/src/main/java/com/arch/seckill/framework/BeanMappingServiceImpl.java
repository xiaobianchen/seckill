package com.arch.seckill.framework;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chenxiaobian on 16/7/1.
 */
public class BeanMappingServiceImpl implements BeanMappingService {

    @Autowired
    private Mapper mapper;

    @Override
    public <T> T transform(Object o, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            mapper.map(o, t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException("转换对象有问题", e);
        }
    }
}
