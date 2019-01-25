package uca.platform.fileserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import uca.platform.fileserver.domain.FileSetInfo;
import uca.platform.json.StdObjectMapper;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by andy.lv
 * on: 2019/1/25 12:40
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private StdObjectMapper stdObjectMapper;

    @Test
    public void save() {
        FileSetInfo fileSetInfo = new FileSetInfo();
        fileSetInfo.setId(UUID.randomUUID().toString());
        fileSetInfo.setFileSrcName("TEST_REDIS");
        fileSetInfo.setCreatedBy("test");
        fileSetInfo.setCreatedOn(LocalDateTime.now());
        redisTemplate.opsForValue().set(fileSetInfo.getId(), stdObjectMapper.toJson(fileSetInfo));

        FileSetInfo result = stdObjectMapper.fromJson(redisTemplate.opsForValue().get(fileSetInfo.getId()), FileSetInfo.class);
        System.out.println(result);
        assertEquals(fileSetInfo.getId(), result.getId());
        assertEquals(fileSetInfo.getFileSrcName(), result.getFileSrcName());
        assertEquals(fileSetInfo.getCreatedBy(), result.getCreatedBy());
        assertEquals(fileSetInfo.getCreatedOn().getYear(), result.getCreatedOn().getYear());
        assertEquals(fileSetInfo.getCreatedOn().getMonth(), result.getCreatedOn().getMonth());
        assertEquals(fileSetInfo.getCreatedOn().getDayOfMonth(), result.getCreatedOn().getDayOfMonth());
        assertEquals(fileSetInfo.getCreatedOn().getHour(), result.getCreatedOn().getHour());
        assertEquals(fileSetInfo.getCreatedOn().getMinute(), result.getCreatedOn().getMinute());
        assertEquals(fileSetInfo.getCreatedOn().getSecond(), result.getCreatedOn().getSecond());
    }
}
