package ejiang.online.publicutils.base.mvp.basepresenter;



import java.lang.ref.WeakReference;

import ejiang.online.publicutils.base.mvp.baseview.MvpView;

/**
 * 用于保持对View的引用
 * @param <V>
 */
public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V>{
    private WeakReference<V> reference;

    @Override
    public void attachView(V view) {
        if (view == null){
            throw new NullPointerException("view can not be null when in attachview() in BasePresenter");
        }
        else {
            //将View置为弱引用，当view被销毁回收时，依赖于view的对象（即Presenter）也会被回收，而不会造成内存泄漏
            if (reference == null){
                reference = new WeakReference<>(view);
            }
        }

    }

    @Override
    public void detachView() {
        if (reference != null) {
            reference.clear();
            reference = null;
        }
    }

    public V getMvpView() {
        if (isAttach()){
            return reference.get();
        }
        else{
            throw new NullPointerException("have you ever called attachView() in BasePresenter");
        }
    }

    public boolean isAttach() {
        return reference != null && reference.get() != null;
    }

}
