package ejiang.online.publicutils.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class AppUtils {


    /** 判断程序是否在后台运行 */
    public static boolean isRunBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    // 表明程序在后台运行
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    /** 判断程序是否在前台运行（当前运行的程序） */
    public static boolean isRunForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null){
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;// 程序运行在前台
            }
        }
        return false;
    }


    public static boolean getCurrentTask(Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //获取当前所有存活task的信息
        List<ActivityManager.RunningTaskInfo> appProcessInfos = activityManager.getRunningTasks(Integer.MAX_VALUE);

        //遍历，若task的name与当前task的name相同，则返回true，否则，返回false
        for (ActivityManager.RunningTaskInfo process : appProcessInfos) {

            if (process.baseActivity.getPackageName().equals(context.getPackageName())
                    || process.topActivity.getPackageName().equals(context.getPackageName())) {

                return true;
            }
        }
        return false;
    }
}
