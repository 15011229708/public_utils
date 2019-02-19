package ejiang.online.publicutils.base.mvp.delegate;


import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;

/**
 * 用于控制MvpPresenter与MvpView
 * @param <P>
 * @param <V>
 */
public class MvpInternalDelegate<P extends MvpPresenter, V extends MvpView> {
    BaseDelegateCallback<P, V> callback;

    public MvpInternalDelegate(BaseDelegateCallback<P, V> callback) {
        this.callback = callback;
    }

    /**
     * 创建Presenter
     * @return
     */
    public P createPresenter() {
        P p = callback.getPresenter();
        if (p == null){
            p = callback.createPresenter();
        }
        if (p == null){
            throw new NullPointerException("callback.createPresenter() is null in MvpInternalDelegate");
        }

        return p;
    }

    /**
     * 绑定view
     */
    public void attachView() {
        callback.getPresenter().attachView(callback.getMvpView());
    }
    /**
     * 解除view
     */
    public void detachView() {
        callback.getPresenter().detachView();
    }
}
