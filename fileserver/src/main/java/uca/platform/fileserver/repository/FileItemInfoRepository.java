package uca.platform.fileserver.repository;

import jooq.generated.fileserver.Tables;
import jooq.generated.fileserver.tables.records.FileItemInfoRecord;
import org.jooq.Configuration;
import org.springframework.stereotype.Repository;
import uca.platform.common.repository.StdRepository;
import uca.platform.fileserver.domain.FileItemInfo;

/**
 * Created by andy.lv
 * on: 2019/1/28 13:39
 */
@Repository
public class FileItemInfoRepository extends StdRepository<FileItemInfoRecord, FileItemInfo> {

    public FileItemInfoRepository(Configuration configuration) {
        super(Tables.FILE_ITEM_INFO, FileItemInfo.class, configuration);
    }

}
