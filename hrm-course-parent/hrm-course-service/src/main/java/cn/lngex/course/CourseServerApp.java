package cn.lngex.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"cn.lngex.course","cn.lngex.search.feign","cn.lngex.auth.myinterception"})
@EnableEurekaClient
@MapperScan("cn.lngex.course.mapper")
@EnableFeignClients(basePackages = "cn.lngex.search.feign")
public class
CourseServerApp {
    public static void main(String[] args) {
        SpringApplication.run(CourseServerApp.class);
    }
}
