package uca.platform.fileserver;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by andy.lv
 * on: 2019/1/25 18:08
 */
@ConfigurationProperties(prefix = "uca.fileserver")
@Configuration
@Data
public class FileServerConfiguration {

    private String url;

    private String tempFilePath;

    private String realFilePath;

}
