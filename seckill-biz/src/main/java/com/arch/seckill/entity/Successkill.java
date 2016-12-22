package com.arch.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Successkill extends BasicEntity {
    private long seckillProductId;
    private long userPhone;
    private int status;
}