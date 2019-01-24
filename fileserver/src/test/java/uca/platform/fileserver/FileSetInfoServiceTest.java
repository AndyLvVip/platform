package uca.platform.fileserver;

import jooq.generated.fileserver.tables.records.FileSetInfoRecord;
import org.jooq.DSLContext;
import org.jooq.exception.DataChangedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uca.platform.fileserver.domain.FileSetInfo;
import uca.platform.fileserver.service.FileSetInfoService;

import static jooq.generated.fileserver.tables.FileSetInfo.FILE_SET_INFO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by andy.lv
 * on: 2019/1/24 17:51
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class FileSetInfoServiceTest {

    @Autowired
    private FileSetInfoService fileSetInfoService;

    @Autowired
    private DSLContext dsl;

    @Test
    public void newRecord() {
        FileSetInfo fileSetInfo = new FileSetInfo();
        fileSetInfo.setFileSrcName("TEST_FILE");
        FileSetInfoRecord record = dsl.newRecord(FILE_SET_INFO, fileSetInfo);
        assertEquals(fileSetInfo.getFileSrcName(), record.getFileSrcName());
    }

    @Test
    public void create() {
        FileSetInfo fileSetInfo = new FileSetInfo();
        fileSetInfo.setFileSrcName("TEST_CREATE");
        FileSetInfo result = fileSetInfoService.create(fileSetInfo, "test");
        FileSetInfo dbResult = fileSetInfoService.fetch(result.getId());
        System.out.println(dbResult);
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

    @Test(expected = DataChangedException.class)
    public void checkDirtyWrite() {
        FileSetInfo fileSetInfo = new FileSetInfo();
        fileSetInfo.setFileSrcName("TEST_EDIT");
        FileSetInfo result = fileSetInfoService.create(fileSetInfo, "test");
        FileSetInfo dbResult = fileSetInfoService.fetch(result.getId());
        System.out.println(dbResult);
        dbResult = fileSetInfoService.edit(dbResult, "test");
        System.out.println(dbResult);
        dbResult.setVersion(dbResult.getVersion() - 1);
        dbResult = fileSetInfoService.edit(dbResult, "test");
        System.out.println(dbResult);
    }
}
