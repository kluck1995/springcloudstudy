package com.dk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
/**
 * nacos 作注册中心
 */
public class ConfigNacosMain3377 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigNacosMain3377.class,args);
    }
}
