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
public abstract class StdRepository<R extends UpdatableRecord<R>, P extends StdDomain> extends DAOImpl<R, P, String> {

    @Override
    protected String getId(P object) {
        return object.getId();
    }

    protected StdRepository(Table<R> table, Class<P> type, Configuration configuration) {
        super(table, type, configuration);
    }

    @Override
    @Deprecated
    public void insert(P object) {
        super.insert(object);
    }

    public void insert(P object, String createdBy) {
        object.setCreatedBy(createdBy);
        object.setUpdatedBy(createdBy);
        create(object);
    }

    private void create(P object) {
        if(null == object.getId() || object.getId().isEmpty())
            object.setId(UUID.randomUUID().toString());
        object.setVersion(1);
        LocalDateTime now = LocalDateTime.now();
        object.setCreatedOn(now);
        object.setUpdatedOn(now);
        super.insert(object);
    }

    @Override
    @Deprecated
    public void update(P object) {
        super.update(object);
    }

    public void update(P object, String updatedBy) {
        object.setUpdatedBy(updatedBy);
        object.setUpdatedOn(LocalDateTime.now());
        super.update(object);
    }
}
