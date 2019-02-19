package ejiang.online.publicutils.base.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Pattern;

import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;
import ejiang.online.publicutils.base.mvp.delegate.activity.ActivityDelegate;
import ejiang.online.publicutils.base.mvp.delegate.activity.ActivityDelegateImp;
import ejiang.online.publicutils.base.mvp.delegate.activity.ActivityMvpDelegateCallback;
import ejiang.online.publicutils.eventbus.BindEventBus;
import ejiang.online.publicutils.eventbus.ExceptionEvent;
import ejiang.online.publicutils.utils.AppUtils;
import ejiang.online.publicutils.utils.LogUtils;
import ejiang.online.publicutils.utils.ToastUtils;

@BindEventBus
public abstract class BaseMvpActivity<P extends MvpPresenter, V extends MvpView> extends AppCompatActivity implements
        ActivityMvpDelegateCallback<P, V> {

    private ActivityDelegate mActivityDelegate;
    private P mPresenter;
    private Activity mActivity;
    protected Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        LogUtils.init(this);
        if(!LogUtils.APP_DBG){
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        ToastUtils.init(this);
        mActivity = this;
        mActivityDelegate = new ActivityDelegateImp<>(this);
        mActivityDelegate.onCreate();
        setContentView(getLayoutResId());
        ActivityCollector.addActivity(this);
        init();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityDelegate.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityDelegate.onStop();
    }


    @Override
    public void setPresenter() {

    }

    //暴露一个创建的方法用于创建presenter
    protected abstract P CreatePresenter();

    @Override
    //这个方法由MvpInternalDelegate 调用 BaseDelegateCallback 来创建Presenter
    public P createPresenter()

    {
        mPresenter = CreatePresenter();
        return mPresenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        mActivityDelegate.onDestroy();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    //初始化
    protected abstract void init();

    //用于获取布局id
    protected abstract int getLayoutResId();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(ExceptionEvent event) {
        if (event.getCode() == 403) {
            if(AppUtils.getCurrentTask(mActivity)){
                Error403(event.getMsg());
            }
        } else {
            Pattern p = Pattern.compile("[a-zA-z]");
            if (!p.matcher(event.getMsg()).find()) {
                ToastUtils.show(event.getMsg());
            }
        }
    }

    //用于处理403异常
    protected abstract void Error403(String msg);


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        XPermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

//    public void initStopPush() {
//        //华为
//        setReceiveNotifyMsg(false);
//        //极光
//        JPushInterface.stopPush(this);
//        //小米
//        MiPushClient.disablePush(this);
//        //魅族
//        /**
//         * @param appId
//         *        push 平台申请的应用id
//         * @param appKey
//         *        push 平台申请的应用key
//         * @param pushId
//         *        注册成功后返回的pushid
//         * @param pushType
//         *        接收的消息类型，0:通知栏消息 1: 透传消息
//         * @param switcher
//         *        修改push类型开关状态
//         * 使用说明：此方法最好只有在用户需要打开或关闭消息时调用，不要频繁调用；当你第一次register成功后，通知栏消息和透传消息已经默认打开
//         * */
//        PushManager.switchPush(this, ContactApi.MEIZU_APP_ID, ContactApi.MEIZU_APP_KEY, PushManager.getPushId(this), 0, false);
//
//    }
//    public void initStartPush() {
//        //华为
//        setReceiveNotifyMsg(true);
//        //极光
//        JPushInterface.resumePush(this);
//        //小米
//        MiPushClient.enablePush(this);
//        //魅族
//        /**
//         * @param appId
//         *        push 平台申请的应用id
//         * @param appKey
//         *        push 平台申请的应用key
//         * @param pushId
//         *        注册成功后返回的pushid
//         * @param pushType
//         *        接收的消息类型，0:通知栏消息 1: 透传消息
//         * @param switcher
//         *        修改push类型开关状态
//         * 使用说明：此方法最好只有在用户需要打开或关闭消息时调用，不要频繁调用；当你第一次register成功后，通知栏消息和透传消息已经默认打开
//         * */
//        PushManager.switchPush(this, ContactApi.MEIZU_APP_ID, ContactApi.MEIZU_APP_KEY, PushManager.getPushId(this), 0, true);
//
//    }
//    /**
//     * 调用设置是否接收push透传消息接口 | Set whether to receive normal pass messages
//     *
//     * @param enable 是否开启 | enabled or not
//     */
//    public void setReceiveNormalMsg(boolean enable) {
//        HMSAgent.Push.enableReceiveNormalMsg(enable, new EnableReceiveNormalMsgHandler() {
//            @Override
//            public void onResult(int rst) {
//                Log.e("HMS", "enableReceiveNormalMsg:end code=" + rst);
//            }
//        });
//    }
//
//    /**
//     * 设置接收通知消息 | Set up receive notification messages
//     *
//     * @param enable 是否开启 | enabled or not
//     */
//    public void setReceiveNotifyMsg(boolean enable) {
//        HMSAgent.Push.enableReceiveNotifyMsg(enable, new EnableReceiveNotifyMsgHandler() {
//            @Override
//            public void onResult(int rst) {
//                Log.e("HMS", "enableReceiveNotifyMsg:end code=" + rst);
//            }
//        });
//    }
}
