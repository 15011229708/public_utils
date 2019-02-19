package ejiang.online.publicutils.base.mvp.delegate.fragment;

/**
 * Fragment的生命周期接口
 */
public interface FragmentDelegate {
    void onCreateView();

    void onPause();

    void onResume();

    void onStop();

    void onDestroyView();
}
