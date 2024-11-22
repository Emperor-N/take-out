package com.phn.mytakeout.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "phn.auth")
@Data
public class AuthProperties {
    private List<String> excludePaths;
    private List<String> includePaths;
}
