package uca.msgpush;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by @author andy
 * On @date 19-2-2 下午12:13
 */
public interface MsgpushStream {

    String INPUT_BIND_USER = "input-bind-user";

    @Input(INPUT_BIND_USER)
    SubscribableChannel inputBindUser();

    @Input("input-unbind-user")
    SubscribableChannel inputUnbindUser();

    @Output("output-bind-user")
    MessageChannel outputBindUser();

    @Output("output-unbind-user")
    MessageChannel outputUnbindUser();

}
