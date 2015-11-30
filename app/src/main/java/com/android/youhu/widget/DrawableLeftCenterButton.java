package com.android.youhu.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 使得drawableleft 居中 Button
 * Created by allipper on 2015/8/24.
 */
public class DrawableLeftCenterButton extends Button {
    public DrawableLeftCenterButton(Context context, AttributeSet attrs,
                                    int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableLeftCenterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableLeftCenterButton(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                setPadding( 0, 0,(int) (getWidth() - bodyWidth), 0);
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
    }
}
