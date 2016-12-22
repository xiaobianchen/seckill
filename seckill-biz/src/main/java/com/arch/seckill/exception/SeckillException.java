package com.arch.seckill.exception;

/**
 * Created by chenxiaobian on 16/12/22.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
