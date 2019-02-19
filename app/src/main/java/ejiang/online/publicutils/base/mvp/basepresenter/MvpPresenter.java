package ejiang.online.publicutils.base.mvp.basepresenter;


import ejiang.online.publicutils.base.mvp.baseview.MvpView;

/**
 * Presenter的基础类，控制的MvpView的子类
 */
public interface MvpPresenter<V extends MvpView>{
    /**
     * 绑定view
     * @param view
     */
    void attachView(V view);

    /**
     * 解除view
     */
    void detachView();
}
