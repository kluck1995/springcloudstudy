package com.dk.springcloud.controller;


import com.dk.entities.CommonResult;
import com.dk.entities.Payment;
import com.dk.springcloud.seivice.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author dk
 */
@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int insert = paymentService.insert(payment);
        if (insert != 0) {
            log.info("插入成功");
            return new CommonResult(200, "Payment数据入库成功,serverPort: " + serverPort, insert);
        }
        return new CommonResult(500, "Payment数据入库失败,serverPort: " + serverPort);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        if (payment != null) {
            log.info("根据id查询Payment成功");
            return new CommonResult(200, "Payment数据查询成功,serverPort: " + serverPort , payment);

        }
        return new CommonResult(500, "Payment数据查询失败，没有对应记录,serverPort: " + serverPort);
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****element: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

    @GetMapping("/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/zipkin")
    public String getPaymentZipkin(){
        return "执行了getPaymentZipkin方法";
    }
}
