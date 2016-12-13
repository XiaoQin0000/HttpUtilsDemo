package cn.android.http.http;

/**
 * Created by QD on 2016/12/13.
 */

public class HttpInit {

    private int connectTime;
    private int readTime;

    private static final class HttpUrlConnectHolder{
        private static final HttpInit INSTANCE = new HttpInit();
    }

    private HttpInit(){}

    public static HttpInit getInstance(){
        return HttpUrlConnectHolder.INSTANCE;
    }

    public int getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(int connectTime) {
        this.connectTime = connectTime;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

}
