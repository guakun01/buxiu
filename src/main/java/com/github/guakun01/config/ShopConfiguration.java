package com.github.guakun01.config;

import com.github.guakun01.buxiu.BuXiuProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BuXiuProperties.class)
@ConditionalOnProperty(name = "buxiu.ready", havingValue = "true")
public class ShopConfiguration {
}
