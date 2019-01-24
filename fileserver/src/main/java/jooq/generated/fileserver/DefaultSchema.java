/*
 * This file is generated by jOOQ.
 */
package jooq.generated.fileserver;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import jooq.generated.fileserver.tables.FileItemInfo;
import jooq.generated.fileserver.tables.FileSetInfo;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 165472419;

    /**
     * The reference instance of <code></code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * The table <code>file_item_info</code>.
     */
    public final FileItemInfo FILE_ITEM_INFO = jooq.generated.fileserver.tables.FileItemInfo.FILE_ITEM_INFO;

    /**
     * The table <code>file_set_info</code>.
     */
    public final FileSetInfo FILE_SET_INFO = jooq.generated.fileserver.tables.FileSetInfo.FILE_SET_INFO;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            FileItemInfo.FILE_ITEM_INFO,
            FileSetInfo.FILE_SET_INFO);
    }
}