package com.yujy.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yujy
 * @title:
 * @projectName
 * @description:
 * @date 2020/10/2319:36
 */
@EnableSwagger2
@SpringBootApplication
@ComponentScan(basePackages = {"com.yujy"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
