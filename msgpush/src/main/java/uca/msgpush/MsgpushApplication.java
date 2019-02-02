package uca.msgpush;

/**
 * Created by @author andy
 * On @date 19-2-2 上午9:38
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import uca.platform.factory.StdObjectFactory;

/**
 * Created by andy.lv
 * on: 2019/1/24 13:53
 */
@SpringBootApplication
@RefreshScope
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
public class MsgpushApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsgpushApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return StdObjectFactory.objectMapper();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter(objectMapper);
        return jackson2JsonMessageConverter;
    }

}
