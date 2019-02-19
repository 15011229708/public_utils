package ejiang.online.publicutils.base.mvp.delegate.activity;

/**
 * 与activity生命周期一致，目的是为了通过activity的生命周期去控制是否要attachView
 */
public interface ActivityDelegate {
    void onCreate();

    void onPause();

    void onResume();

    void onStop();

    void onDestroy();
}
