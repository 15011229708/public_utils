package ejiang.online.publicutils.views.tablayout;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by yanglin on 2018/5/29.
 */

public class AnimationUtils {

    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final Interpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new FastOutSlowInInterpolator();
    public static final Interpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
    public static final Interpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();
    public static final Interpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();

    /**
     * Linear interpolation between {@code startValue} and {@code endValue} by {@code fraction}.
     */
    public static float lerp(float startValue, float endValue, float fraction) {
        return startValue + (fraction * (endValue - startValue));
    }

    public static int lerp(int startValue, int endValue, float fraction) {
        return startValue + Math.round(fraction * (endValue - startValue));
    }



    public static void showVisibility(View view){
//        TranslateAnimation showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 1.0f);
//        showAnim.setDuration(500);
//        view.startAnimation(showAnim);
        view.setVisibility(View.VISIBLE);
    }


    public static void showGone(View view){
//        TranslateAnimation hideAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 1.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f);
//        hideAnim.setDuration(500);
//        view.startAnimation(hideAnim);
        view.setVisibility(View.GONE);
    }
}
