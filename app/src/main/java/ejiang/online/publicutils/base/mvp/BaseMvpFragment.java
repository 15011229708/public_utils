package ejiang.online.publicutils.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.greenrobot.eventbus.EventBus;

import ejiang.online.publicutils.base.mvp.basepresenter.MvpPresenter;
import ejiang.online.publicutils.base.mvp.baseview.MvpView;
import ejiang.online.publicutils.base.mvp.delegate.fragment.FragmentDelegate;
import ejiang.online.publicutils.base.mvp.delegate.fragment.FragmentDelegateImp;
import ejiang.online.publicutils.base.mvp.delegate.fragment.FragmentMvpDelegateCallback;
import ejiang.online.publicutils.eventbus.BindEventBus;

public abstract class BaseMvpFragment<P extends MvpPresenter, V extends MvpView> extends Fragment implements
        FragmentMvpDelegateCallback<P, V> {
    private FragmentDelegate mFragmentDelegate;
    private P mPresenter;
    public BaseMvpActivity mActivity;
    /**
     * 控件是否初始化完成
     */
    protected boolean isPrepared = false;
    /**
     * 是否第一次进入
     */
    protected boolean isFirst = true;
    /**
     * 是否显示
     */
    protected boolean isVisible = true;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared=true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        if(this.getUserVisibleHint()) {
            this.isVisible = true;
            this.onVisibles();
        } else {
            this.isVisible = false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(this.getUserVisibleHint()) {
            this.isVisible = true;
            this.onVisibles();
        } else {
            this.isVisible = false;
            this.onVisibles();
        }

    }
    /**
     * fragement可见时调用
     */
    protected void onVisibles() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        this.isFirst = false;
        init();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentDelegate = new FragmentDelegateImp<>(this);
        mFragmentDelegate.onCreateView();
        View view = inflater.inflate(getLayoutResId(), container, false);
        if(this.getActivity() instanceof BaseMvpActivity) {
            this.mActivity = (BaseMvpActivity)this.getActivity();
        }
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().register(this);
        }
//        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        mFragmentDelegate.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentDelegate.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFragmentDelegate.onStop();
    }

    protected abstract void init();

    protected abstract int getLayoutResId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mFragmentDelegate.onDestroyView();
        if (this.getClass().isAnnotationPresent(BindEventBus.class)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void setPresenter() {

    }

    //暴露一个创建的方法用于创建presenter
    protected abstract P CreatePresenter();

    @Override
    //这个方法由MvpInternalDelegate 调用BaseDelegateCallback 来创建presenter
    public P createPresenter() {
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

}
