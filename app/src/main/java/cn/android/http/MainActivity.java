package cn.android.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import cn.android.http.callback.HttpCallBack;
import cn.android.http.model.CityModel;
import cn.android.http.model.HttpError;
import cn.android.http.rest.TestRest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestRest.getCityList(new HttpCallBack<CityModel>() {
            @Override
            public void onSuccess(CityModel model) {
                Log.e("MainActivity", "success:model:" + model);
            }

            @Override
            public void onFail(HttpError error) {
                if (!TextUtils.isEmpty(error.getCode())) {
                    Log.e("MainActivity", "fail:error:" + error.getCode());
                } else {
                    error.printStackTrace();
                }
            }
        });

    }
}
