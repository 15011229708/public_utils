package ejiang.online.publicutils.Interceptor;

import android.util.Log;

import ejiang.online.publicutils.eventbus.ExceptionEvent;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request oldRequest = chain.request();
        try {
            Response response = chain.proceed(oldRequest);
            byte[] respBytes = response.body()
                    .bytes();
            switch (response.code()) {
                case 200:
                case 403:
                    String respString = new String(respBytes);
                    Log.e("respString===",respString);
                    JSONObject object = new JSONObject(respString);
                    if(object.has("errcode")){

                    }else if(object.has("status")){
                        int status = (int) object.opt("status");
                        String msg = (String) object.opt("msg");
                        switch (status){
                            case 1:
                                break;
                            case 0:
                                EventBus.getDefault().postSticky(new ExceptionEvent(0,msg));
                                break;
                            case 403: {
                                EventBus.getDefault().postSticky(new ExceptionEvent(403,msg));
                                break;
                            }
                            default:
                                break;
                        }
                    }
                    break;
                case 500:
                case 404:
                    EventBus.getDefault().postSticky(new ExceptionEvent(0000000000,"服务器繁忙，请稍后再试"));
                    break;
            }

            return response.newBuilder()
                    .body(ResponseBody.create(null, respBytes))
                    .build();//在前面获取bytes的时候response的stream已经被关闭了,要重新生成response
        } catch (Exception e) {
            if (e instanceof ConnectException || e instanceof UnknownHostException) {
                EventBus.getDefault().postSticky(new ExceptionEvent(0000000000,"服务器繁忙，请稍后再试"));
            } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
                EventBus.getDefault().postSticky(new ExceptionEvent(0000000000,"服务器繁忙，请稍后再试"));
            }
            e.printStackTrace();
            return null;
        }
    }
}