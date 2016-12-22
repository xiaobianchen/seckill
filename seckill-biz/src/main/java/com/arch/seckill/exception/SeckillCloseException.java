package com.arch.seckill.exception;

/**
 * Created by chenxiaobian on 16/12/22.
 *
 * 秒杀通道关闭异常处理
 */
public class SeckillCloseException extends RuntimeException {

    public SeckillCloseException(String message){
        super(message);
    }

    public SeckillCloseException(String message,Throwable throwable){
        super(message,throwable);
    }
}
