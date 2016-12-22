package com.arch.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seckill extends BasicEntity {
    private long seckillId;
    private String name;
    private int number;
    private Date startTime;
    private Date endTime;
}