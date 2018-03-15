package com.kevin.snake.bootlicense.exception;


import com.kevin.snake.bootlicense.enums.SysMsgEnum;

/**
 * Created by tangqiuyuan on 2017/4/8.
 */
public class BusinessException extends RuntimeException {
    private int code;
    private String message;

    public BusinessException(SysMsgEnum sysMsgEnum) {
        this(sysMsgEnum.getCode(), sysMsgEnum.getMessage());
    }

    public BusinessException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BusinessException(SysMsgEnum sysMsgEnum, Exception e) {
        super(e.getMessage());
        this.code = sysMsgEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
