package com.cormye.client;

import com.cormye.client.config.NettyClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NettyClientProperties.class)
public class ClientApplication {

    public static void main(String[] args) {

        //SpringApplication.run(ClientApplication.class, args);
        //不占用端口启动
        new SpringApplicationBuilder(ClientApplication.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .run(args);
    }

}
