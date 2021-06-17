package cn.lngex.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("cn.lngex.system.mapper")
@EnableEurekaClient
public class SystemServerApp {
    public static void main(String[] args) {
        SpringApplication.run(SystemServerApp.class);
    }
}
