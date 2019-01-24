package uca.platform.common.jooq;

import org.jooq.conf.Settings;

/**
 * Created by @author andy
 * On @date 19-1-25 上午12:29
 */
public class ObjectFactoryUtils {

    public static Settings settings() {
        return new Settings().withExecuteWithOptimisticLocking(true).withExecuteWithOptimisticLockingExcludeUnversioned(true);
    }
}
