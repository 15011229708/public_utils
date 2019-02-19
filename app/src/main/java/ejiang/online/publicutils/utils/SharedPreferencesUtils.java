package ejiang.online.publicutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
	public static String datastore = "user";
	public static String rememberpass = "pass";
	
	//保存数据
	public static void put(Context context, String key, String value, String datastore){
		SharedPreferences setting = context.getSharedPreferences(datastore, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	//读取数据
	public static String getString(Context context, String key, String datastore) {
        SharedPreferences settings = context.getSharedPreferences(datastore, Context.MODE_PRIVATE);
        String arg = settings.getString(key, "0");
        return arg;  
    }  
	
	//清空数据
	public static void clear(Context context, String datastore){
		SharedPreferences setting = context.getSharedPreferences(datastore, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = setting.edit();
	    editor.clear();  
	    editor.commit();
	}
}
