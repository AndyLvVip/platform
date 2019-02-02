package uca.msgpush.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import uca.msgpush.MsgpushStream;
import uca.msgpush.vo.BindUnbindUserVo;

/**
 * Created by @author andy
 * On @date 19-2-2 下午12:09
 */
@Service
@EnableBinding(MsgpushStream.class)
public class BindingUser {

    private static final Logger LOG = LoggerFactory.getLogger(BindingUser.class);

    @StreamListener(MsgpushStream.INPUT_BIND_USER)
    public void bindUser(BindUnbindUserVo bindUnbindUserVo) {
        LOG.info("bindingUnbindingUser:{}" + bindUnbindUserVo);
    }
}
