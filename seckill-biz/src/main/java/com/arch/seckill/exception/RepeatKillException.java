package com.arch.seckill.exception;

/**
 * Created by chenxiaobian on 16/12/22.
 *
 * 重复秒杀异常处理
 */
public class RepeatKillException extends RuntimeException {

    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message,Throwable throwable){
        super(message,throwable);
    }


}
