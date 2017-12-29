package com.sh.pri.exceptions;

/**
 * Created by admin on 2017/12/29.
 * 自定义异常类
 */
public class PriException extends RuntimeException{
    private static final long serialVersionUID = -3116234610382074052L;

    private String code;
    private String message;

    public PriException(String message) {
        super(message);
        this.message = message;
    }

    public PriException(String code, String message){
        super(message);
        this.message = message;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
