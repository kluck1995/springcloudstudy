package com.dk.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 如果PaymentHystrixService 调用正常，返回结果就是正常结果
 *           ...             调用异常，返回以下处理结果
 *
 * @author dk
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fall paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fall paymentInfo_TimeOut";
    }
}
