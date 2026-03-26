package com.lighting.metal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 雷霆五金商城后端启动类
 * 开启MyBatis扫描 + Redis缓存
 */
@SpringBootApplication
@MapperScan("com.lighting.metal.mapper")  // 扫描Mapper接口
@EnableCaching  // 开启Redis缓存
public class LightingMetalApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightingMetalApplication.class, args);
        System.out.println("======= 雷霆五金跨境商城后端启动成功 =======");
    }
}