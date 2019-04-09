package com.cormye.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: cormye
 * @Date: 2019-04-09 13:14
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "netty.client")
public class NettyClientProperties {

    private String serverIp;
    private int port;


}
