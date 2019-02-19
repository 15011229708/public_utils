package ejiang.online.publicutils.Interceptor;

import android.text.TextUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = "";
        if(TextUtils.isEmpty(token)){
            Request request = chain.request().newBuilder().addHeader("authorization", "").build();
            return chain.proceed(request);
        }
        Request request = chain.request().newBuilder().addHeader("authorization", token).build();
        return chain.proceed(request);
    }
}
