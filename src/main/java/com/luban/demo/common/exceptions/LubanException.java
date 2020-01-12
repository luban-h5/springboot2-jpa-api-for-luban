package com.luban.demo.common.exceptions;

import lombok.Data;

/**
 * Lu banException鲁班异常
 *
 * @author Yang Hao
 * @date 2020/1/12 17:40
 */
@Data
public class LubanException extends RuntimeException {

    private static final long serialVersionUID = -3576785908021342999L;

    private String error;

    public LubanException() {
        super();
    }

    public LubanException(String error) {
        super(error);
        this.error = error;
    }

    public LubanException(String error, String message) {
        super(message);
        this.error = error;
    }

    public LubanException(String message, Throwable cause) {
        super(message, cause);
    }

    public LubanException(Throwable cause) {
        super(cause);
    }

}
