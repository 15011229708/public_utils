package ejiang.online.publicutils.base.mvp;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    public static List<AppCompatActivity> activitys =
            new ArrayList<>();

    public static void addActivity(AppCompatActivity activity){
        activitys.add(activity);
    }

    public static void removeActivity(AppCompatActivity activity){
        if(activitys.contains(activity)){
            activitys.remove(activity);
        }
    }
    public static List<AppCompatActivity> getActivity(){
        return activitys;
    }
}
