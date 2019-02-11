/*
 * This file is generated by jOOQ.
 */
package jooq.generated.fileserver;


import jooq.generated.fileserver.tables.FileItemInfo;
import jooq.generated.fileserver.tables.FileSetInfo;
import jooq.generated.fileserver.tables.records.FileItemInfoRecord;
import jooq.generated.fileserver.tables.records.FileSetInfoRecord;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;

import javax.annotation.Generated;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FileItemInfoRecord> KEY_FILE_ITEM_INFO_PRIMARY = UniqueKeys0.KEY_FILE_ITEM_INFO_PRIMARY;
    public static final UniqueKey<FileSetInfoRecord> KEY_FILE_SET_INFO_PRIMARY = UniqueKeys0.KEY_FILE_SET_INFO_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<FileItemInfoRecord> KEY_FILE_ITEM_INFO_PRIMARY = Internal.createUniqueKey(FileItemInfo.FILE_ITEM_INFO, "KEY_file_item_info_PRIMARY", FileItemInfo.FILE_ITEM_INFO.ID);
        public static final UniqueKey<FileSetInfoRecord> KEY_FILE_SET_INFO_PRIMARY = Internal.createUniqueKey(FileSetInfo.FILE_SET_INFO, "KEY_file_set_info_PRIMARY", FileSetInfo.FILE_SET_INFO.ID);
    }
}
