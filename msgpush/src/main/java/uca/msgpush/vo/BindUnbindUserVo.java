package uca.msgpush.vo;

import lombok.Data;

/**
 * Created by @author andy
 * On @date 19-2-2 下午12:54
 */
@Data
public class BindUnbindUserVo {
    private Integer type;

    private String username;

    public enum Type {

        BIND(1),
        UNBIND(2),
        ;

        public final int val;

        Type(int value) {
            val = value;
        }
    }
}
