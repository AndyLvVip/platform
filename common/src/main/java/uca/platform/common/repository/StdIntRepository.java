package uca.platform.common.repository;

import org.jooq.Configuration;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import uca.platform.common.domain.StdIntDomain;

public class StdIntRepository<R extends UpdatableRecord<R>, P extends StdIntDomain> extends StdRepository<R, P, Integer> {
    protected StdIntRepository(Table<R> table, Class<P> type, Configuration configuration) {
        super(table, type, configuration);
    }

    @Override
    protected Integer getId(P object) {
        return object.getId();
    }

    @Override
    protected void create(P object) {
        super.create(object);
    }
}
