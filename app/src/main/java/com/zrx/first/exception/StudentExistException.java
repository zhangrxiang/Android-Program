package com.zrx.first.exception;

/**
 * @author zhangrxiang
 * @version 1.0
 * @since 2015/10/18 11:55
 */
public class StudentExistException extends Exception {
    public StudentExistException() {
    }

    public StudentExistException(String detailMessage) {
        super(detailMessage);
    }

    public StudentExistException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public StudentExistException(Throwable throwable) {
        super(throwable);
    }
}
