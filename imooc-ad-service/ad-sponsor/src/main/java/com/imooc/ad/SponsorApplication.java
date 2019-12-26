package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //这个注解，这个微服务可以调用其他微服务，在dashboard中可以实现监控
@EnableCircuitBreaker //调度器，也是为了实现监控
@EnableEurekaClient //标识它是一个eureka client
@SpringBootApplication //这是一个springboot的application

public class SponsorApplication {
  public static void main(String[] args) {
    SpringApplication.run(SponsorApplication.class, args);
  }
}
