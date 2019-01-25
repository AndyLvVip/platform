package uca.platform.fileserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.conf.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import uca.platform.common.jooq.ObjectFactoryUtils;
import uca.platform.factory.StdObjectFactory;
import uca.platform.json.StdObjectMapper;

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

    public static void main(String[] args) {
        SpringApplication.run(FileServerApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return StdObjectFactory.objectMapper();
    }

    @Bean
    public StdObjectMapper stdObjectMapper(ObjectMapper objectMapper) {
        return new StdObjectMapper(objectMapper);
    }

    @Bean
    public Settings settings() {
        return ObjectFactoryUtils.settings();
    }

}