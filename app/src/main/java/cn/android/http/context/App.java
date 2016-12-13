package cn.android.http.context;

import android.app.Application;

import cn.android.http.http.HttpRequest;

/**
 * Created by QD on 2016/12/14.
 */

public class App extends Application {

    private static final int TIME_HTTP_CONNECT = 8 * 1000;
    private static final int TIME_HTTP_READ = 8 * 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        HttpRequest.getInstance().setConnectTime(TIME_HTTP_CONNECT);
        HttpRequest.getInstance().setReadTime(TIME_HTTP_READ);
    }
}
