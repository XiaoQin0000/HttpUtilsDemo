package cn.android.http.http;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.util.Map;

import cn.android.http.callback.HttpCallBack;
import cn.android.http.excutor.HttpExecutor;
import cn.android.http.model.BaseModel;
import cn.android.http.model.HttpError;
import cn.android.http.model.HttpHeaders;

/**
 * Created by QD on 2016/12/13.
 */

public class HttpBase {

    protected static void checkUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("url is null !!!");
        }
    }

    protected static void addHeaders(HttpURLConnection conn, HttpHeaders headers) {
        if (!HttpHeaders.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    protected static <Model extends BaseModel> void resultSuccess(InputStream is, final HttpCallBack<Model> callBack) {
        String resultData = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = is.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            resultFail(new HttpError(e), callBack);
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        if (!TextUtils.isEmpty(resultData) && null != callBack) {
            final Class<?> cs = (Class<?>) ((ParameterizedType) callBack.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            final Model model = (Model) new Gson().fromJson(resultData, cs);
            HttpExecutor.getInstance().resultPost(new Runnable() {
                @Override
                public void run() {
                    callBack.onSuccess(model);
                }
            });
        }
    }

    protected static <Model extends BaseModel> void resultFail(final HttpError error, final HttpCallBack<Model> callBack) {
        if (null != callBack) {
            HttpExecutor.getInstance().resultPost(new Runnable() {
                @Override
                public void run() {
                    callBack.onFail(error);
                }
            });
        }
    }

}
