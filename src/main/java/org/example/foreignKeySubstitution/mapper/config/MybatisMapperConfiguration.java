package org.example.foreignKeySubstitution.mapper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mapper")
@PropertySource("classpath:mybatis-mapper.properties")
public class MybatisMapperConfiguration {
}
