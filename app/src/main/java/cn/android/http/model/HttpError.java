package cn.android.http.model;

/**
 * Created by QD on 2016/12/13.
 */

public class HttpError extends Exception{

    private String code;
    private String msg;

    public HttpError() {
    }

    public HttpError(Throwable cause) {
        super(cause);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
