/*
 * This file is generated by jOOQ.
 */
package jooq.generated.corporate.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import jooq.generated.corporate.DefaultSchema;
import jooq.generated.corporate.Indexes;
import jooq.generated.corporate.Keys;
import jooq.generated.corporate.tables.records.CorporateRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Corporate extends TableImpl<CorporateRecord> {

    private static final long serialVersionUID = -713085707;

    /**
     * The reference instance of <code>corporate</code>
     */
    public static final Corporate CORPORATE = new Corporate();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CorporateRecord> getRecordType() {
        return CorporateRecord.class;
    }

    /**
     * The column <code>corporate.id</code>.
     */
    public final TableField<CorporateRecord, String> ID = createField("id", org.jooq.impl.SQLDataType.CHAR(36).nullable(false), this, "");

    /**
     * The column <code>corporate.name</code>.
     */
    public final TableField<CorporateRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>corporate.enabled</code>.
     */
    public final TableField<CorporateRecord, Boolean> ENABLED = createField("enabled", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>corporate.status</code>.
     */
    public final TableField<CorporateRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>corporate.type</code>.
     */
    public final TableField<CorporateRecord, Integer> TYPE = createField("type", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>corporate.createdBy</code>.
     */
    public final TableField<CorporateRecord, String> CREATEDBY = createField("createdBy", org.jooq.impl.SQLDataType.VARCHAR(30).nullable(false), this, "");

    /**
     * The column <code>corporate.createdOn</code>.
     */
    public final TableField<CorporateRecord, LocalDateTime> CREATEDON = createField("createdOn", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>corporate.version</code>.
     */
    public final TableField<CorporateRecord, Integer> VERSION = createField("version", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>corporate.updatedBy</code>.
     */
    public final TableField<CorporateRecord, String> UPDATEDBY = createField("updatedBy", org.jooq.impl.SQLDataType.VARCHAR(30).nullable(false), this, "");

    /**
     * The column <code>corporate.updatedOn</code>.
     */
    public final TableField<CorporateRecord, LocalDateTime> UPDATEDON = createField("updatedOn", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * Create a <code>corporate</code> table reference
     */
    public Corporate() {
        this(DSL.name("corporate"), null);
    }

    /**
     * Create an aliased <code>corporate</code> table reference
     */
    public Corporate(String alias) {
        this(DSL.name(alias), CORPORATE);
    }

    /**
     * Create an aliased <code>corporate</code> table reference
     */
    public Corporate(Name alias) {
        this(alias, CORPORATE);
    }

    private Corporate(Name alias, Table<CorporateRecord> aliased) {
        this(alias, aliased, null);
    }

    private Corporate(Name alias, Table<CorporateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Corporate(Table<O> child, ForeignKey<O, CorporateRecord> key) {
        super(child, key, CORPORATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CORPORATE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CorporateRecord> getPrimaryKey() {
        return Keys.KEY_CORPORATE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CorporateRecord>> getKeys() {
        return Arrays.<UniqueKey<CorporateRecord>>asList(Keys.KEY_CORPORATE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Corporate as(String alias) {
        return new Corporate(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Corporate as(Name alias) {
        return new Corporate(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Corporate rename(String name) {
        return new Corporate(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Corporate rename(Name name) {
        return new Corporate(name, null);
    }
}
