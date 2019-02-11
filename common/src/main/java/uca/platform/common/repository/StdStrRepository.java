package uca.platform.common.repository;

import org.jooq.Configuration;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import uca.platform.StdStringUtils;
import uca.platform.common.domain.StdStrDomain;

public class StdStrRepository<R extends UpdatableRecord<R>, P extends StdStrDomain> extends StdRepository<R, P, String> {


    protected StdStrRepository(Table<R> table, Class<P> type, Configuration configuration) {
        super(table, type, configuration);
    }

    @Override
    protected String getId(P object) {
        return object.getId();
    }

    @Override
    protected void create(P object) {
        if(null == object.getId() || object.getId().isEmpty())
            object.setId(StdStringUtils.uuid());
        super.create(object);
    }
}
