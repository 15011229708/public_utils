package ejiang.online.publicutils.base.mvp.delegate.activity;


import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;
import ejiang.online.publicutils.base.mvp.delegate.BaseDelegateCallback;

/**
 * 生命周期回调
 * @param <P>
 * @param <V>
 */
public interface ActivityMvpDelegateCallback <P extends MvpPresenter, V extends MvpView> extends BaseDelegateCallback<P, V> {
}
