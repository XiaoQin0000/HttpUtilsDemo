package cn.android.http.http;

import cn.android.http.callback.HttpCallBack;
import cn.android.http.model.BaseModel;
import cn.android.http.model.HttpHeaders;
import cn.android.http.model.HttpParams;

/**
 * Created by QD on 2016/12/13.
 */

public class HttpRequest<Model extends BaseModel> {
    private static final class HttpRequestHolder {
        private static final HttpRequest INSTANCE = new HttpRequest();
    }

    private HttpRequest() {
    }

    public static HttpRequest getInstance() {
        return HttpRequestHolder.INSTANCE;
    }

    public void setReadTime(int readTime) {
        HttpInit.getInstance().setReadTime(readTime);
    }

    public void setConnectTime(int connectTime) {
        HttpInit.getInstance().setConnectTime(connectTime);
    }

    public void get(String url, HttpHeaders headers, HttpParams params, HttpCallBack<Model> callBack) {
        HttpGet.get(url, headers, params, callBack);
    }

    public void post(String url, HttpHeaders headers, HttpParams params, HttpCallBack<Model> callBack) {
        HttpPost.post(url, headers, params, callBack);
    }


}
