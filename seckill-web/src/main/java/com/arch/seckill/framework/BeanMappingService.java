package com.arch.seckill.framework;

/**
 * Created by chenxiaobian on 16/7/1.
 */
public interface BeanMappingService {

    <T> T transform(Object o, Class<T> clazz);

}
