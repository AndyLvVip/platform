package uca.platform.fileserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.conf.Settings;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import uca.platform.common.jooq.JooqUtils;
import uca.platform.factory.StdObjectFactory;
import uca.platform.json.StdObjectMapper;
import uca.platform.redis.StdStringRedisTemplate;

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
        return JooqUtils.settings();
    }

    @Bean
    public StdStringRedisTemplate stdStringRedisTemplate(StringRedisTemplate stringRedisTemplate
                                                         , RedissonClient redissonClient
    ) {
        StdStringRedisTemplate stdStringRedisTemplate = new StdStringRedisTemplate(stringRedisTemplate, redissonClient);
        return stdStringRedisTemplate;
    }

    @Bean
    public UserInfoTokenServices userInfoTokenServices(ResourceServerProperties sso
                                                       , UserInfoRestTemplateFactory restTemplateFactory
//                                                       , PrincipalExtractor principalExtractor
    ) {
        UserInfoTokenServices services = new UserInfoTokenServices(
                sso.getUserInfoUri(), sso.getClientId());
        services.setRestTemplate(restTemplateFactory.getUserInfoRestTemplate());
        services.setTokenType(sso.getTokenType());
//        if (principalExtractor != null) {
//            services.setPrincipalExtractor(principalExtractor);
//        }
        return services;
    }

}
