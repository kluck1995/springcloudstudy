package com.dk.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dk.entities.CommonResult;
import com.dk.entities.Payment;

/**
 * 自定义限流处理类
 */

public class Customerhandler {
    public CommonResult handlerException(BlockException exception) {
        return new CommonResult(444,"按客户自定义，global handlerException",new Payment(2020L,"serial003"));
    }

    public CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444,"按客户自定义2，global handlerException2",new Payment(2020L,"serial003"));
    }
}
