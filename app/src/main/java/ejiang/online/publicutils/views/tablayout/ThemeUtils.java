package ejiang.online.publicutils.views.tablayout;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by yanglin on 2018/5/29.
 */

public class ThemeUtils {

    private static final int[] APPCOMPAT_CHECK_ATTRS = {
            android.support.v7.appcompat.R.attr.colorPrimary
    };

    public static void checkAppCompatTheme(Context context) {
        TypedArray a = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !a.hasValue(0);
        a.recycle();
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme "
                    + "(or descendant) with the design library.");
        }
    }
}
