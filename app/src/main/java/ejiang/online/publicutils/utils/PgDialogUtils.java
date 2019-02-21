package ejiang.online.publicutils.utils;

import android.app.Dialog;
import android.content.Context;

import ejiang.online.publicutils.R;


public class PgDialogUtils {
    private static Dialog mPgDialog;
    public static void init(Context context) {
        mPgDialog = new Dialog(context, R.style.AlertDialogStyle);
        mPgDialog.setContentView(R.layout.splash_loading);
        mPgDialog.setCanceledOnTouchOutside(false);
    }

    public static void show() {
        if(mPgDialog!=null){
            mPgDialog.show();
        }

    }
    public static void dismiss() {
        if(mPgDialog!=null&&mPgDialog.isShowing()){
            mPgDialog.dismiss();
        }

    }
}
