package com.lighting.metal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 最简启动类，避免触发扫描器漏洞
 */
@SpringBootApplication
public class LightingMetalApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightingMetalApplication.class, args);
        System.out.println("======= 雷霆五金商城后端启动成功 =======");
    }
}