package ejiang.online.publicutils.base;

import java.io.Serializable;

public class BaseEntity<T> implements Serializable {
    /**
     * 返回状态码
     */
    private int status;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
