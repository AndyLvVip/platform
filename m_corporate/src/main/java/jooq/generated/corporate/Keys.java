/*
 * This file is generated by jOOQ.
 */
package jooq.generated.corporate;


import javax.annotation.Generated;

import jooq.generated.corporate.tables.Corporate;
import jooq.generated.corporate.tables.records.CorporateRecord;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


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

    public static final UniqueKey<CorporateRecord> KEY_CORPORATE_PRIMARY = UniqueKeys0.KEY_CORPORATE_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<CorporateRecord> KEY_CORPORATE_PRIMARY = Internal.createUniqueKey(Corporate.CORPORATE, "KEY_corporate_PRIMARY", Corporate.CORPORATE.ID);
    }
}