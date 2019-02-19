package ejiang.online.publicutils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
    /**
     * 检查网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if(context == null){
            return true;
        }
        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        return networkinfo != null && networkinfo.isAvailable();
    }
}
