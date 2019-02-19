package ejiang.online.publicutils.eventbus;


public class ExceptionEvent {
    private int code;

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

    private String msg;
    public ExceptionEvent(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
}
