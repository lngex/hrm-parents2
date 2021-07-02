package cn.lngex.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cn.lngex.utils.aop","cn.lngex.system","cn.lngex.auth.domain"})
@MapperScan("cn.lngex.system.mapper")
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.lngex.auth.feign")
public class SystemServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemServerApp.class);
    }
}