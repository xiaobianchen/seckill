package com.arch.seckill.enums;

/**
 * Created by chenxiaobian on 16/12/22.
 */
public enum SecKillEnum {
    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"服务器内部错误"),
    DATA_REWRITE(-3,"数据重复写入");

    private int code;
    private String desc;

    SecKillEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
