package com.wywk.myautotest.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: HttpClientService
 * @Author: zsj
 * @Since:  2020/6/11 01:22
 * @Description:
 *
 * HttpClient 这是一个工具类
 * Configuration: 标明这是一个配置类
 * ConfigurationProperties: 加载application.yml 配置文件
 *      prefix: 前缀
 *      ignoreUnknownFields: 忽略找不到的属性
 *
 * 1. SpringBoot推荐使用RestTemplate操作RestFul API
 * 2. RestTemplate底层需要借助HttpClient创建
 */

@Configuration
@ConfigurationProperties(prefix = "http", ignoreUnknownFields = true)
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
