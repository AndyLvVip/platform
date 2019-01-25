package uca.platform.exception;

/**
 * Created by andy.lv
 * on: 2019/1/25 16:51
 */
public class StdException extends RuntimeException {
    public StdException(String message) {
        super(message);
    }

    public StdException(String message, Throwable cause) {
        super(message, cause);
    }

    public StdException(Throwable cause) {
        super(cause);
    }
}
