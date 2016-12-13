package cn.android.http.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import cn.android.http.callback.HttpCallBack;
import cn.android.http.excutor.HttpExecutor;
import cn.android.http.model.BaseModel;
import cn.android.http.model.HttpError;
import cn.android.http.model.HttpHeaders;
import cn.android.http.model.HttpParams;

/**
 * Created by QD on 2016/12/13.
 */

public class HttpGet extends HttpBase {

    public static <Model extends BaseModel> void get(String url, final HttpHeaders headers, HttpParams params, final HttpCallBack<Model> callBack) {
        checkUrl(url);
        final String prefectUrl = getGetUrl(url, params);
        HttpExecutor.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL mUrl = new URL(prefectUrl);
                    conn = (HttpURLConnection) mUrl.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(HttpInit.getInstance().getConnectTime());
                    conn.setReadTimeout(HttpInit.getInstance().getReadTime());
                    addHeaders(conn, headers);
                    conn.connect();
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        InputStream is = conn.getInputStream();
                        resultSuccess(is, callBack);
                    } else {
                        HttpError httpError = new HttpError();
                        httpError.setCode(String.valueOf(responseCode));
                        resultFail(httpError, callBack);
                    }
                } catch (Exception e) {
                    HttpError httpError = new HttpError();
                    httpError.initCause(e);
                    resultFail(httpError, callBack);
                } finally {
                    if (null != conn) {
                        conn.disconnect();
                    }
                }
            }
        });
    }

    private static String getGetUrl(String url, HttpParams params) {
        StringBuilder urlSb = new StringBuilder(url);
        if (null != params) {
            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
                if (urlSb.toString().contains("?")) {
                    urlSb.append("&");
                } else {
                    urlSb.append("?");
                }
                urlSb.append(paramEntry.getKey()).append("=").append(paramEntry.getValue());
            }
        }
        return urlSb.toString();
    }

}
