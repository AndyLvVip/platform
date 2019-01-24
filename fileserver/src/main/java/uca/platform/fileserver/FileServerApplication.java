package uca.platform.fileserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import uca.platform.util.BeanUtils;

/**
 * Created by andy.lv
 * on: 2019/1/24 13:53
 */
@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
public class FileServerApplication {

    @Bean
    public ObjectMapper objectMapper() {
        return BeanUtils.objectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class, args);
    }

}