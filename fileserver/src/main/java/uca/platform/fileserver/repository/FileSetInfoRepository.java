package uca.platform.fileserver.repository;

import jooq.generated.fileserver.tables.records.FileSetInfoRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import uca.platform.common.repository.StdRepository;
import uca.platform.fileserver.domain.FileSetInfo;

import static jooq.generated.fileserver.Tables.FILE_SET_INFO;

/**
 * Created by andy.lv
 * on: 2019/1/24 16:20
 */
@Repository
public class FileSetInfoRepository extends StdRepository<FileSetInfoRecord, FileSetInfo> {

    private DSLContext dsl;

    public FileSetInfoRepository(Configuration configuration, DSLContext dsl) {
        super(FILE_SET_INFO, FileSetInfo.class, configuration);
        this.dsl = dsl;
    }

}
