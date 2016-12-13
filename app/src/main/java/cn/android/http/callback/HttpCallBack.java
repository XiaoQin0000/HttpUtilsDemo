package cn.android.http.callback;

import cn.android.http.model.BaseModel;
import cn.android.http.model.HttpError;

/**
 * Created by QD on 2016/12/13.
 */

public abstract class HttpCallBack<Model extends BaseModel>  {

    public abstract void onSuccess(Model model);

    public abstract void onFail(HttpError error);

}
