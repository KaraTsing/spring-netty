package com.cormye;

import com.cormye.server.config.NettyServerProperties;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(NettyServerProperties.class)
public class ServerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ServerApplication.class, args);
        //不占用端口启动
        new SpringApplicationBuilder(ServerApplication.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .run(args);
    }

}
