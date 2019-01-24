package uca.platform.fileserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uca.platform.fileserver.domain.FileSetInfo;
import uca.platform.fileserver.service.FileSetInfoService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andy.lv
 * on: 2019/1/24 17:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileSetInfoServiceTest {

    @Autowired
    private FileSetInfoService fileSetInfoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void create() throws JsonProcessingException {
        FileSetInfo fileSetInfo = new FileSetInfo();
        fileSetInfo.setFileSrcName("TEST_FILE");
        FileSetInfo result = fileSetInfoService.create(fileSetInfo, "test");
        FileSetInfo dbResult = fileSetInfoService.fetch(result.getId());
        System.out.println(objectMapper.writeValueAsString(dbResult));
        assertNotNull(dbResult);
        assertEquals(result.getId(), dbResult.getId());
        assertEquals(result.getFileSrcName(), dbResult.getFileSrcName());
        assertEquals(result.getCreatedOn().getYear(), dbResult.getCreatedOn().getYear());
        assertEquals(result.getCreatedOn().getMonth(), dbResult.getCreatedOn().getMonth());
        assertEquals(result.getCreatedOn().getDayOfMonth(), dbResult.getCreatedOn().getDayOfMonth());
        assertEquals(result.getCreatedOn().getHour(), dbResult.getCreatedOn().getHour());
        assertEquals(result.getCreatedOn().getMinute(), dbResult.getCreatedOn().getMinute());
        assertEquals(result.getCreatedOn().getSecond(), dbResult.getCreatedOn().getSecond());
        assertEquals(result.getCreatedBy(), dbResult.getCreatedBy());
        assertEquals(result.getUpdatedBy(), dbResult.getUpdatedBy());
        assertEquals(fileSetInfo.getFileSrcName(), result.getFileSrcName());
    }
}
