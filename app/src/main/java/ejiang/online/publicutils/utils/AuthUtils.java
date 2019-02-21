package ejiang.online.publicutils.utils;

import android.text.TextUtils;

public class AuthUtils {


    /**
     * 权限数据转换
     * String转数组
     */
    public static Integer[] getAuth(String auth){
        Integer[] auths = null;
        if(TextUtils.isEmpty(auth)){
            String[] authStrings = auth.split(",");
            for (int i = 0; i < authStrings.length; i++) {
                auths[i] = Integer.parseInt(authStrings[i]);
            }
        }
        return auths;
    }
}
