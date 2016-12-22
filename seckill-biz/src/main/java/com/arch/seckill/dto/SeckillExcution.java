package com.arch.seckill.dto;

import com.arch.seckill.entity.Successkill;

/**
 * Created by chenxiaobian on 16/12/22.
 */

public class SeckillExcution {
    private long seckillId;

    private int status;

    private String statusInfo;

    private Successkill successKill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Successkill getSuccessKill() {
        return successKill;
    }

    public void setSuccessKill(Successkill successKill) {
        this.successKill = successKill;
    }

    public SeckillExcution(long seckillId, String statusInfo) {
        this.seckillId = seckillId;
        this.statusInfo = statusInfo;
    }

    public SeckillExcution(long seckillId, int status, Successkill successKill) {
        this.seckillId = seckillId;
        this.status = status;
        this.successKill = successKill;
    }
}
