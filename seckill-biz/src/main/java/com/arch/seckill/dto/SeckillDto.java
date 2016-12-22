package com.arch.seckill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chenxiaobian on 16/12/22.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeckillDto implements Serializable{
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
    private Date createTime;
}
