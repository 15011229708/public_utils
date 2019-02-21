package ejiang.online.publicutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.Map;

import ejiang.online.publicutils.bean.CompanyBean;
import ejiang.online.publicutils.bean.UserBean;


public class SharedPreferencesUtil {

    // 所要保存数据的文件名
    public static String FILLNAME = "save";

    /**
     * 存入某个key对应的value值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value) {
        put(context, key, value, FILLNAME, Context.MODE_PRIVATE);
    }

    public static void put(Context context, String key, Object value,
                           String fileName) {
        put(context, key, value, fileName, Context.MODE_PRIVATE);
    }

    public static void put(Context context, String key, Object value, int mode) {
        put(context, key, value, FILLNAME, mode);
    }

    public static void put(Context context, String key, Object value,
                           String fileName, int mode) {
        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        }
        editor.apply();
        // editor.commit(); 同步效率低
    }

    /**
     * 得到某个key对应的值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */

    public static Object get(Context context, String key, Object defValue) {
        return get(context, key, defValue, FILLNAME, Context.MODE_PRIVATE);
    }

    public static Object get(Context context, String key, Object defValue,
                             String fileName) {
        return get(context, key, defValue, fileName, Context.MODE_PRIVATE);
    }

    public static Object get(Context context, String key, Object defValue,
                             int mode) {
        return get(context, key, defValue, FILLNAME, mode);
    }

    public static Object get(Context context, String key, Object defValue,
                             String fileName, int mode) {
        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
        if (defValue instanceof String) {
            return sp.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sp.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return sp.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sp.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 返回所有数据
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        return getAll(context, FILLNAME, Context.MODE_PRIVATE);
    }

    public static Map<String, ?> getAll(Context context, String fileName) {
        return getAll(context, fileName, Context.MODE_PRIVATE);
    }

    public static Map<String, ?> getAll(Context context, String fileName,
                                        int mode) {
        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
        return sp.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        remove(context, key, FILLNAME, Context.MODE_PRIVATE);
    }

    public static void remove(Context context, String key, String fileName) {
        remove(context, key, fileName, Context.MODE_PRIVATE);
    }

    public static void remove(Context context, String key, String fileName,
                              int mode) {
        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有内容
     *
     * @param context
     */
    public static void clear(Context context) {
        clear(context, FILLNAME, Context.MODE_PRIVATE);
    }

    public static void clear(Context context, String fileName) {
        clear(context, fileName, Context.MODE_PRIVATE);
    }

    public static void clear(Context context, String fileName, int mode) {
        SharedPreferences sp = context.getSharedPreferences(fileName, mode);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }


    /**
     * 保存UserBean
     * @param context
     * @param userBean
     */

    public static void putUser(Context context, UserBean userBean) {
        UserBean oldUserBean = getUserBean(context);
        if(oldUserBean != null){
            if(!TextUtils.isEmpty(userBean.getNickName())){
                oldUserBean.setNickName(userBean.getNickName());
            }
            if(!TextUtils.isEmpty(userBean.getPhone())){
                oldUserBean.setPhone(userBean.getPhone());
            }
            if(!TextUtils.isEmpty(userBean.getHeaderUrl())){
                oldUserBean.setHeaderUrl(userBean.getHeaderUrl());
            }
            if(!TextUtils.isEmpty(userBean.getToken())){
                oldUserBean.setToken(userBean.getToken());
            }
            if(userBean.getAuth()!=null){
                if(userBean.getAuth().length>0){
                    oldUserBean.setAuth(userBean.getAuth());
                }
            }
            if(userBean.getAge()!=null){
                oldUserBean.setAge(userBean.getAge());
            }
            if(userBean.getWorkerAge()!=null){
                oldUserBean.setWorkerAge(userBean.getWorkerAge());
            }
            if(userBean.getUserId()!=null){
                oldUserBean.setUserId(userBean.getUserId());
            }

        }
        Gson gson = new Gson();
        put(context, "UserBean", gson.toJson(oldUserBean), FILLNAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取UserBean
     * @param context
     * @return
     */
    public static UserBean getUserBean(Context context){
        Gson gson = new Gson();
        UserBean userBean = gson.fromJson((String) get(context, "UserBean", "", FILLNAME, Context.MODE_PRIVATE),UserBean.class);
        if(userBean == null){
            userBean = new UserBean();
        }
        return userBean;
    }


    /**
     * 保存CompanyBean
     * @param context
     * @param companyBean
     */

    public static void putCompany(Context context, CompanyBean companyBean) {
        CompanyBean oldCompanyBean = getCompanyBean(context);
        if(oldCompanyBean != null){
            if(!TextUtils.isEmpty(oldCompanyBean.getCompanyAddress())){
                oldCompanyBean.setCompanyAddress(companyBean.getCompanyAddress());
            }
            if(!TextUtils.isEmpty(oldCompanyBean.getCompanyBoss())){
                oldCompanyBean.setCompanyBoss(companyBean.getCompanyBoss());
            }
            if(!TextUtils.isEmpty(oldCompanyBean.getCompanyLogo())){
                oldCompanyBean.setCompanyLogo(companyBean.getCompanyLogo());
            }
            if(!TextUtils.isEmpty(oldCompanyBean.getCompanyName())){
                oldCompanyBean.setCompanyName(companyBean.getCompanyName());
            }

            if(companyBean.getCompanyId()!=null){
                oldCompanyBean.setCompanyId(companyBean.getCompanyId());
            }

        }
        Gson gson = new Gson();
        put(context, "CompanyBean", gson.toJson(oldCompanyBean), FILLNAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取CompanyBean
     * @param context
     * @return
     */
    public static CompanyBean getCompanyBean(Context context)  {
        Gson gson = new Gson();
        CompanyBean companyBean = gson.fromJson((String) get(context, "CompanyBean", "", FILLNAME, Context.MODE_PRIVATE),CompanyBean.class);
        if(companyBean == null){
            companyBean = new CompanyBean();
        }
        return companyBean;
    }

}
