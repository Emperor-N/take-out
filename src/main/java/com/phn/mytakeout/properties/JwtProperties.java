package com.phn.mytakeout.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "phn.jwt")
@Data
public class JwtProperties {
    private String secret;
    private long ttl;
}
