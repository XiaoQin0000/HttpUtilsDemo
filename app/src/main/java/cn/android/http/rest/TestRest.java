package cn.android.http.rest;

import cn.android.http.callback.HttpCallBack;
import cn.android.http.http.HttpRequest;
import cn.android.http.model.CityModel;
import cn.android.http.model.HttpHeaders;
import cn.android.http.model.HttpParams;

/**
 * Created by QD on 2016/12/14.
 */

public class TestRest {

    private static final String HEADER_APIKEY = "96acb62ece5b8f98315ef1cfc34d5dc2";

    public static void getCityList(HttpCallBack<CityModel> callBack) {
        String url = "http://apis.baidu.com/apistore/weatherservice/citylist";
        HttpHeaders mHttpHeaders = new HttpHeaders();
        mHttpHeaders.add("apikey", HEADER_APIKEY);
        HttpParams mHttpParams = new HttpParams();
        mHttpParams.add("cityname", "广东");
        HttpRequest.getInstance().get(url, mHttpHeaders, mHttpParams, callBack);
    }
}
