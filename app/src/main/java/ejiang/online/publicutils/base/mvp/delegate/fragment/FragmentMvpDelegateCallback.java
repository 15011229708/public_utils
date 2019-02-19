package ejiang.online.publicutils.base.mvp.delegate.fragment;


import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;
import ejiang.online.publicutils.base.mvp.delegate.BaseDelegateCallback;

/**
 * Fragment的生命周期回调
 * @param <P>
 * @param <V>
 */
public interface FragmentMvpDelegateCallback<P extends MvpPresenter, V extends MvpView>
        extends BaseDelegateCallback<P, V> {
}
