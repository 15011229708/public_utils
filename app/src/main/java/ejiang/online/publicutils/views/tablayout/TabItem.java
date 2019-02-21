package ejiang.online.publicutils.views.tablayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;


/**
 * TabItem is a special 'view' which allows you to declare tab items for a {@link TabLayout}
 * within a layout. This view is not actually added to TabLayout, it is just a dummy which allows
 * setting of a tab items's text, icon and custom layout. See TabLayout for more information on how
 * to use it.
 *
 * @attr ref android.support.design.R.styleable#TabItem_android_icon
 * @attr ref android.support.design.R.styleable#TabItem_android_text
 * @attr ref android.support.design.R.styleable#TabItem_android_layout
 *
 * @see TabLayout
 */

public class TabItem extends View {
    public final CharSequence mText;
    public final Drawable mIcon;
    public final int mCustomLayout;

    public TabItem(Context context) {
        this(context, null);
    }
    @SuppressLint("RestrictedApi")
    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);

         final TintTypedArray a = TintTypedArray.obtainStyledAttributes(context, attrs,
                android.support.design.R.styleable.TabItem);
        mText = a.getText(android.support.design.R.styleable.TabItem_android_text);
        mIcon = a.getDrawable(android.support.design.R.styleable.TabItem_android_icon);
        mCustomLayout = a.getResourceId(android.support.design.R.styleable.TabItem_android_layout, 0);
        a.recycle();
    }
}
