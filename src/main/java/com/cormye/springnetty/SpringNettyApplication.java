package com.cormye.springnetty;

import com.cormye.springnetty.config.NettyClientProperties;
import com.cormye.springnetty.config.NettyServerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({NettyClientProperties.class, NettyServerProperties.class})
public class SpringNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNettyApplication.class, args);
    }

}
