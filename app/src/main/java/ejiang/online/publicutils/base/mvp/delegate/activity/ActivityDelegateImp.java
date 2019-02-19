package ejiang.online.publicutils.base.mvp.delegate.activity;


import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;
import ejiang.online.publicutils.base.mvp.delegate.BaseDelegateCallback;
import ejiang.online.publicutils.base.mvp.delegate.MvpInternalDelegate;

/**
 * 实现了ActivityDelegate，在生命周期里控制presenter与MvpView
 */
public class ActivityDelegateImp<P extends MvpPresenter, V extends MvpView> implements ActivityDelegate {
    private BaseDelegateCallback<P, V> basemvpDelegateCallback;
    private MvpInternalDelegate<P, V> mvpInternalDelegate;


    //传入BaseDelegateCallback 去控制Presenter
    public ActivityDelegateImp(BaseDelegateCallback<P, V> basemvpDelegateCallback) {
        if (basemvpDelegateCallback == null)
            throw new NullPointerException("the basemvpDelegateCallback in ActivityDelegateImpn is null");
        this.basemvpDelegateCallback = basemvpDelegateCallback;
        mvpInternalDelegate = new MvpInternalDelegate<>(this.basemvpDelegateCallback);

    }

    @Override
    public void onCreate() {
        mvpInternalDelegate.createPresenter();
        mvpInternalDelegate.attachView();

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        mvpInternalDelegate.detachView();
    }
}
