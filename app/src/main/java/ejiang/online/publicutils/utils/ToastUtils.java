package ejiang.online.publicutils.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    private static Toast mToast;

    public static void init(Context context) {
        if(mToast==null){
            mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
        }
    }

    public static void show(int resId) {
        mToast.setText(resId);
        mToast.show();
    }

    public static void show(CharSequence charSequence) {
        mToast.setText(charSequence);
        mToast.show();
    }
}
