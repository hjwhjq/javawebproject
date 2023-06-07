package com.example.springboot02.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("seat")
public class Seat {
    @TableId(value = "totalid",type= IdType.AUTO)
    private Integer totalid;
    private Integer seatid;
    private String status;
    private String reading;
}
