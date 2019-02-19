package ejiang.online.publicutils.base.mvp.delegate;


import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;

/**
 * 用于对获取MvpView、创建以及获取presenter
 * @param <P>
 * @param <V>
 */
public interface BaseDelegateCallback<P extends MvpPresenter,V extends MvpView> {
    void setPresenter();

    P getPresenter();

    P createPresenter();

    V getMvpView();
}
