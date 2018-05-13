package com.kevin.snake.bootlicense.enums;

public enum ProcessResultEnum {

    SUCCESS(0, "操作成功"),
    INS_FAIL(101, "添加失败"),
    UPD_FAIL(102, "修改失败"),
    DEL_FAIL(103, "删除失败"),
    QUE_FAIL(104,"查询异常"),
    LOGIN_FAIL(-1, "用户名或密码错误");

    private int code;
    private String msg;

    ProcessResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取异常的类名+方法
     *
     * @return 类名+类方法
     */
    public static final String getClassPath() {
        String currentMethod = "";
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        if (stack.length > 1) {
            for (int i = 1; i < 2; i++) {
                StackTraceElement ste = stack[i];
                currentMethod = currentMethod + ste.getClassName() + "." + ste.getMethodName() + "(...);";
            }
        } else {
        }
        return currentMethod;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}


