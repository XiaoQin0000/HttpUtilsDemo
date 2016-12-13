package cn.android.http.excutor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by QD on 2016/12/13.
 */

public class HttpExecutor {

    private Handler mHandler;

    private static final class HttpExecutorHolder {
        private static final HttpExecutor INSTANCE = new HttpExecutor();
    }

    private static final int NUMBER = 4;

    private ExecutorService executorService;

    private HttpExecutor(){
        executorService = Executors.newFixedThreadPool(NUMBER);
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static HttpExecutor getInstance(){
        return HttpExecutorHolder.INSTANCE;
    }

    public void submit(Runnable task){
        executorService.submit(task);
    }

    public void resultPost(Runnable runnable){
        mHandler.post(runnable);
    }

}
