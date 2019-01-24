package uca.platform.corporate.portal.service;

import org.springframework.stereotype.Service;
import uca.platform.corporate.module.domain.Corporate;

/**
 * Created by andy.lv
 * on: 2019/1/24 13:25
 */
@Service
public class CorporateService {

    public Corporate create(Corporate corporate) {
        return corporate;
    }

}
