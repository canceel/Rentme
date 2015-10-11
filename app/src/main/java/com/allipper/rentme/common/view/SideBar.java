package com.allipper.rentme.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.allipper.rentme.R;

import java.util.List;



/**
 * */
public class SideBar extends View {
    private List<String> alphabet;
    private Paint paint;
    private float widthCenter;
    private float height;
    private float textSize;
    private float mScaledDensity;
    private int choose = 0;
    private Context context;

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        widthCenter = getMeasuredWidth() / 2;
        height = getMeasuredHeight() / alphabet.size();
        // textSize = height - height / 3;
        if (alphabet.size() > 0) {
            for (int i = 0; i < alphabet.size(); i++) {
                if (i == choose) {
                    paint.setColor(context.getResources().getColor(R.color.title_background));
                    paint.setFakeBoldText(true);
                    textSize = 13 * mScaledDensity;
                } else {
                    paint.setColor(context.getResources().getColor(R.color.text_normal));
                    paint.setFakeBoldText(false);
                    textSize = 11 * mScaledDensity;
                }
                paint.setTextSize(textSize);
                canvas.drawText(alphabet.get(i), widthCenter, (float) (i + 0.5) * height +
                        textSize / 2, paint);
            }
        }
        super.onDraw(canvas);
    }

    public void setCurrentItem(int position) {
        choose = position;
        invalidate();
    }
}