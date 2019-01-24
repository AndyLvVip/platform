package uca.platform.common.repository;

import org.jooq.Configuration;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DAOImpl;
import uca.platform.common.domain.StdDomain;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by andy.lv
 * on: 2019/1/24 17:23
 */
public abstract class StdRepository<R extends UpdatableRecord<R>, P extends StdDomain, T> extends DAOImpl<R, P, T> {
    protected StdRepository(Table<R> table, Class<P> type) {
        super(table, type);
    }

    protected StdRepository(Table<R> table, Class<P> type, Configuration configuration) {
        super(table, type, configuration);
    }

    @Override
    @Deprecated
    public void insert(P object) {
        super.insert(object);
    }

    public void create(P object, String createdBy) {
        object.setCreatedBy(createdBy);
        object.setUpdatedBy(createdBy);
        create(object);
    }

    private void create(P object) {
        object.setId(UUID.randomUUID().toString());
        LocalDateTime now = LocalDateTime.now();
        object.setCreatedOn(now);
        object.setUpdatedOn(now);
        super.insert(object);
    }
}
