package com.cormye.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: cormye
 * @Date: 2019-04-09 13:14
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "netty.server")
public class NettyServerProperties {

    private int port;
    

}
