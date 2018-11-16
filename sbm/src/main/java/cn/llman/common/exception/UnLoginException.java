package cn.llman.common.exception;

/**
 * @author
 * @date 2018/11/15
 */
public class UnLoginException extends RuntimeException {

    private static final long serialVersionUID = -4482684589802924766L;

    public UnLoginException() {
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

    public UnLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
