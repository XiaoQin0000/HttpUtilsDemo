package cn.android.http.model;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by QD on 2016/12/13.
 */

public class HttpParams {

    private Map<String, String> params;

    public HttpParams(){
        this.params = new HashMap<>();
    }

    public HttpParams add(String key,String value){
        if (TextUtils.isEmpty(key)){
            throw new NullPointerException("key is null!!!");
        }
        params.put(key,value);
        return this;
    }

    public int size(){
        return params.size();
    }

    public Set<Map.Entry<String, String>> entrySet(){
        return params.entrySet();
    }

    public static boolean isEmpty(HttpParams params){
        return params == null || params.size() == 0;
    }
}
