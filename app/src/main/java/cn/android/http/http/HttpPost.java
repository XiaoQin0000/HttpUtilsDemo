package cn.android.http.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

public class HttpPost extends HttpBase {

    public static <Model extends BaseModel> void post(final String url, final HttpHeaders headers, final HttpParams params, final HttpCallBack<Model> callBack) {
        checkUrl(url);
        HttpExecutor.getInstance().submit(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL mUrl = new URL(url);
                    conn = (HttpURLConnection) mUrl.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setConnectTimeout(HttpInit.getInstance().getConnectTime());
                    conn.setReadTimeout(HttpInit.getInstance().getReadTime());
                    addHeaders(conn, headers);
                    if (!HttpParams.isEmpty(params)) {
                        conn.setDoOutput(true);
                        StringBuilder paramSb = new StringBuilder();
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            if (paramSb.length() > 0) {
                                paramSb.append("&");
                            }
                            paramSb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                        }
                        OutputStream outputStream = conn.getOutputStream();
                        outputStream.write(paramSb.toString().getBytes());
                        outputStream.flush();
                        outputStream.close();
                    }
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

}
