package test.msgpush;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import uca.msgpush.MsgpushApplication;
import uca.msgpush.MsgpushStream;
import uca.msgpush.vo.BindUnbindUserVo;

/**
 * Created by @author andy
 * On @date 19-2-2 下午12:46
 */
@SpringBootTest(classes = MsgpushApplication.class)
@RunWith(SpringRunner.class)
public class InputBindingUserTest {

    @Autowired
    MsgpushStream msgpushStream;

    @Test
    public void bindUser() {
        BindUnbindUserVo user = new BindUnbindUserVo();
        user.setType(BindUnbindUserVo.Type.BIND.val);
        user.setUsername("andy");
        msgpushStream.outputBindUser().send(MessageBuilder.withPayload(user).build());
    }

}
