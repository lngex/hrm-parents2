package cn.lngex.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "cn.lngex")
@EnableEurekaClient
@MapperScan("cn.lngex.course.mapper")
public class CourseServerApp {
    public static void main(String[] args) {
        SpringApplication.run(CourseServerApp.class);
    }
}
