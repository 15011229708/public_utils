package ejiang.online.publicutils.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ejiang.online.publicutils.views.DetermineAndCancelDialog;


/**
 * Project Name: XPermissionUtils.java
 * Author: LinZhang
 * Date: 2017/6/15 11:54
 * Notes:
 *
 * @see <a href="https://github.com/AndSync/XPermissionUtils"></a>
 * 权限请求
 */
@TargetApi(Build.VERSION_CODES.M)
public class XPermissionUtils {

    private static final String TAG = "XPermissionUtils";

    private static int mRequestCode = -1; // 权限请求码
    private static int mActivityRequestCode = -1; // activity 开启码
    private static OnPermissionListener mOnPermissionListener;
    private static String[] opsManagers = new String[]{AppOpsManager.OPSTR_FINE_LOCATION
            , AppOpsManager.OPSTR_READ_EXTERNAL_STORAGE, AppOpsManager.OPSTR_WRITE_EXTERNAL_STORAGE
            , AppOpsManager.OPSTR_CALL_PHONE
            , AppOpsManager.OPSTR_CAMERA
            , AppOpsManager.OPSTR_READ_CONTACTS, AppOpsManager.OPSTR_WRITE_CONTACTS
            , AppOpsManager.OPSTR_READ_SMS, AppOpsManager.OPSTR_SEND_SMS
            , AppOpsManager.OPSTR_READ_CALENDAR, AppOpsManager.OPSTR_WRITE_CALENDAR
            , AppOpsManager.OPSTR_RECORD_AUDIO, AppOpsManager.OPSTR_FINE_LOCATION
            , AppOpsManager.OPSTR_COARSE_LOCATION, AppOpsManager.OPSTR_READ_PHONE_STATE};
    private static String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION
            , Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.CALL_PHONE
            , Manifest.permission.CAMERA
            , Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS
            , Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS
            , Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR
            , Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION
            , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE,};
    private static HashMap<String, String> permissionMap = new HashMap<String, String>() {
        {
            put(permissions[0], opsManagers[0]);
            put(permissions[1], opsManagers[1]);
            put(permissions[2], opsManagers[2]);
            put(permissions[3], opsManagers[3]);
            put(permissions[4], opsManagers[4]);
            put(permissions[5], opsManagers[5]);
            put(permissions[6], opsManagers[6]);
            put(permissions[7], opsManagers[7]);
            put(permissions[8], opsManagers[8]);
            put(permissions[9], opsManagers[9]);
            put(permissions[10], opsManagers[10]);
            put(permissions[11], opsManagers[11]);
            put(permissions[12], opsManagers[12]);
            put(permissions[13], opsManagers[13]);
            put(permissions[14], opsManagers[14]);
        }
    };
    private static ArrayList<String> currentPermissions = new ArrayList<>();

    public static final int SD_PERMISSION_REQUEST_CODE = 701;
    public static final int SD_SETTING_REQUEST_CODE = 702;
    public static final int CONTACT_PERMISSION_REQUEST_CODE = 703;
    public static final int CONTACT_SETTING_REQUEST_CODE = 704;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 705;
    public static final int CAMERA_SETTING_REQUEST_CODE = 706;
    public static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 707;
    public static final int CALL_PHONE_SETTING_REQUEST_CODE = 708;
    public static final int EXTERNAL_STORAGE = 709;

    public static void showGetPermissionsDialog(final Activity context, String permissionString
            , final List<String> permissions, final int requestCode) {
        final DetermineAndCancelDialog determineAndCancelDialog = new DetermineAndCancelDialog(context);
        determineAndCancelDialog.isShowContent(true, "你已禁止使用" + permissionString + "权限，请前往设置授予权限。");
        determineAndCancelDialog.isShowCancle(true, "取消");
        determineAndCancelDialog.isShowDetermine(true, "设置");
        determineAndCancelDialog.setDialogButtonListener(new DetermineAndCancelDialog.DialogButtonListener() {
            @Override
            public void doConfirm() {
                determineAndCancelDialog.dismiss();
                XPermissionUtils.turnOnSettings(context, permissions, requestCode);
            }

            @Override
            public void doCancel() {

            }
        });

        determineAndCancelDialog.show();
    }

    public static void showGetPermissionDialog(final Activity context, String permissionString, final String permission, final int requestCode) {
        final DetermineAndCancelDialog determineAndCancelDialog = new DetermineAndCancelDialog(context);
        determineAndCancelDialog.isShowContent(true, "需要打开" + permissionString + "，才能使用此功能。");
        determineAndCancelDialog.isShowCancle(true, "取消");
        determineAndCancelDialog.isShowDetermine(true, "设置");
        determineAndCancelDialog.setDialogButtonListener(new DetermineAndCancelDialog.DialogButtonListener() {
            @Override
            public void doConfirm() {
                determineAndCancelDialog.dismiss();
                XPermissionUtils.turnOnSettings(context, permission, requestCode);
            }

            @Override
            public void doCancel() {

            }
        });
        determineAndCancelDialog.show();
    }

    /**
     * 权限请求的回调
     */
    public interface OnPermissionListener {

        void onPermissionGranted();

        void onPermissionDenied(String[] deniedPermissions, boolean alwaysDenied);
    }

    public static void requestPermissionsAgain(@NonNull Context context, @NonNull String[] permissions,
                                               @NonNull int requestCode) {
        if (context instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
        } else {
            throw new IllegalArgumentException("Context must be an Activity");
        }
    }

    /**
     * 请求权限
     * <p>
     * 先判断请求的权限组当中那些权限未授权，然后再去请求未授权的权限
     *
     * @param context     当前activity
     * @param requestCode 请求码
     * @param permissions 权限组
     * @param listener    回调监听
     */
    public static void requestPermissions(@NonNull Context context, @NonNull int requestCode,
                                          @NonNull String[] permissions, OnPermissionListener listener) {
        mRequestCode = requestCode;
        mOnPermissionListener = listener;
        String[] deniedPermissions = getDeniedPermissions(context, permissions);
        if (deniedPermissions.length > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissionsAgain(context, permissions, requestCode);
        } else {
            if (mOnPermissionListener != null) {mOnPermissionListener.onPermissionGranted();}
        }
    }

    public static void requestPermission(Context context, int requestCode, String permission, OnPermissionListener listener) {
        mRequestCode = requestCode;
        mOnPermissionListener = listener;
        String deniedPermission = getDeniedPermission(context, permission);
        if (!TextUtils.isEmpty(deniedPermission) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissionsAgain(context, new String[]{permission}, requestCode);
        } else {
            if (mOnPermissionListener != null) {mOnPermissionListener.onPermissionGranted();}
        }
    }

    public static void requestPermissionsSingle(@NonNull Context context, @NonNull int requestCode, @NonNull String[] permissions) {
        String[] deniedPermissions = getDeniedPermissions(context, permissions);
        if (deniedPermissions.length > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissionsAgain(context, permissions, requestCode);
        }
    }
    public static boolean checkPermissions(@NonNull Context context, @NonNull String permissions) {
            if (ActivityCompat.checkSelfPermission(context,permissions)!= PackageManager.PERMISSION_GRANTED) {
                return false;
            }
            return true;
    }

    /**
     * 请求权限结果，对应Activity中onRequestPermissionsResult()方法。
     * 在BaseActivity中回调，如果不想这样的话，就在权限请求activity回调
     *
     * @param context      当前请求权限的activity
     * @param requestCode  权限请求码
     * @param permissions  请求的权限数组
     * @param grantResults 同意权限的数组
     */
    public static void onRequestPermissionsResult(@NonNull Activity context, int requestCode,
                                                  @NonNull String[] permissions, int[] grantResults) {
        if (mRequestCode != -1 && requestCode == mRequestCode) {
            if (mOnPermissionListener != null) {
                String[] deniedPermissions = getDeniedPermissions(context, permissions);
                if (deniedPermissions.length > 0) {
                    boolean alwaysDenied = hasAlwaysDeniedPermission(context, permissions);
                    mOnPermissionListener.onPermissionDenied(deniedPermissions, alwaysDenied);
                } else {
//                    mOnPermissionListener.onPermissionGranted();
                }
            }
        }
    }

    public static void onActivityResult(Activity context, int requestCode, int resultCode, Intent data) {
        if (mActivityRequestCode != -1 && requestCode == mActivityRequestCode) {
            String[] strings = currentPermissions.toArray(new String[currentPermissions.size()]);
            if (mOnPermissionListener != null) {
                String[] deniedPermissions = getDeniedPermissions(context, strings);
                if (deniedPermissions.length > 0) {
                    boolean alwaysDenied = hasAlwaysDeniedPermission(context, strings);
                    mOnPermissionListener.onPermissionDenied(deniedPermissions, alwaysDenied);
                } else {
                    mOnPermissionListener.onPermissionGranted();
                }
            }
        }
    }

    public static void turnOnSettings(Activity context, List<String> permissions, int requestCode) {
        mActivityRequestCode = requestCode;
        currentPermissions.clear();
        currentPermissions.addAll(permissions);
        //判断是否为小米系统
        if (TextUtils.equals(BrandUtils.getSystemInfo().getOs(), BrandUtils.SYS_MIUI)) {
            Intent miuiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            miuiIntent.putExtra("extra_pkgname", context.getPackageName());
            //检测是否有能接受该Intent的Activity存在
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(miuiIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfos.size() > 0) {
                context.startActivityForResult(miuiIntent, requestCode);
                return;
            }
        }
        //   跳转系统的设置界面
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivityForResult(intent, requestCode);
    }

    public static void turnOnSettings(Activity context, String permission, int requestCode) {
        mActivityRequestCode = requestCode;
        currentPermissions.clear();
        currentPermissions.add(permission);
        //判断是否为小米系统
        if (TextUtils.equals(BrandUtils.getSystemInfo().getOs(), BrandUtils.SYS_MIUI)) {
            Intent miuiIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            miuiIntent.putExtra("extra_pkgname", context.getPackageName());
            //检测是否有能接受该Intent的Activity存在
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentActivities(miuiIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfos.size() > 0) {
                context.startActivityForResult(miuiIntent, requestCode);
                return;
            }
        }
        //   跳转系统的设置界面
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取请求权限中需要授权的权限
     *
     * @param context     context
     * @param permissions 请求的所有权限组
     * @return 需要授权的权限组
     */
    private static String[] getDeniedPermissions(@NonNull Context context, @NonNull String[] permissions) {
        List<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
//            int i = ActivityCompat.checkSelfPermission(context, permission);
            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permission);
            }
//            if (!checkPermissionIsAllow(context, permissionMap.get(permission), permission)) {
//                deniedPermissions.add(permission);
//            }
//            if (TextUtils.equals(BrandUtils.getSystemInfo().getOs(), BrandUtils.SYS_MIUI)) {
//                if (!checkPermissionIsAllow(context, permissionMap.get(permission), permission)) {
//                    deniedPermissions.add(permission);
//                }
//            } else {
//                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                    deniedPermissions.add(permission);
//                }
//            }
        }
        return deniedPermissions.toArray(new String[deniedPermissions.size()]);
    }

    private static String getDeniedPermission(Context context, String permission) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            return permission;
        }
//        if (!checkPermissionIsAllow(context, permissionMap.get(permission), permission)) {
//                return permission;
//            }
//        if (TextUtils.equals(BrandUtils.getSystemInfo().getOs(), BrandUtils.SYS_MIUI)) {
//            if (!checkPermissionIsAllow(context, permissionMap.get(permission), permission)) {
//                return permission;
//            }
//        } else {
//            if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
//                return permission;
//            }
//        }

        return "";
    }

    /**
     * 根据 ops 和 checkSelfPermission 共同检测权限
     * 同时获取权限，才表示权限是真的获取了
     * 注意opString 与 permission的对应
     *
     * @param opString   ops string
     * @param permission manifest permission string
     * @return is allow
     */
    public static boolean checkPermissionIsAllow(Context context, String opString, String permission) {
        AppOpsManager opsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int checkOpNoThrow = opsManager.checkOpNoThrow(opString, Process.myUid(), context.getPackageName());
        int selfPermission = ActivityCompat.checkSelfPermission(context, permission);
        return checkOpNoThrow == AppOpsManager.MODE_ALLOWED
                && selfPermission == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 判断某一项权限点击了不在提醒
     * 当然不会适配MIUI，好在结果不是很离谱
     *
     * @param context           context
     * @param deniedPermissions 要检测的权限
     * @return true(彻底拒绝) or false(还可再次请求)
     */
    private static boolean hasAlwaysDeniedPermission(@NonNull Context context, @NonNull String... deniedPermissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {return false;}
        boolean rationale;
        for (String permission : deniedPermissions) {
            rationale = ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
            if (!rationale) {return true;}
        }
        return false;
    }
}