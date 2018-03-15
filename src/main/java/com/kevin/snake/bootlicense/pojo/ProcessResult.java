package com.kevin.snake.bootlicense.pojo;

import com.kevin.snake.bootlicense.enums.ProcessResultEnum;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProcessResult<T> implements Serializable {

    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @Description: TODO
     */
    private static final long serialVersionUID = 18329183219L;

    /**
     * 状态码,0:成功  -1:异常(未成功)
     */
    private Integer resultcode;

    /**
     * 操作描述
     */
    private String resultmessage;

    /**
     * 模板携带对象
     */
    private T resultobject;

    public Integer getResultcode() {
        return resultcode;
    }

    public void setResultcode(Integer resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public T getResultobject() {
        return resultobject;
    }

    public void setResultobject(T resultobject) {
        this.resultobject = resultobject;
    }

    public ProcessResult setResult(ProcessResultEnum resultEnum) {
        this.resultcode = resultEnum.getCode();
        this.resultmessage = resultEnum.getMsg();
        return this;
    }

    public ProcessResult setResult(ProcessResultEnum resultEnum, T obj){
        this.resultcode = resultEnum.getCode();
        this.resultmessage = resultEnum.getMsg();
        this.resultobject = obj;
        return this;
    }

    @Override
    public String toString() {
        return "ProcessResult{" +
                "resultcode=" + resultcode +
                ", resultmessage='" + resultmessage + '\'' +
                ", resultobject=" + resultobject +
                '}';
    }
}
