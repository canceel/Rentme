package com.android.youhu.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.GridView;

public class NoRequsetGridView extends GridView {
	public NoRequsetGridView(Context context) {
		super(context);
	}

	public NoRequsetGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoRequsetGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
		if (gainFocus && previouslyFocusedRect != null) {
			super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		}
	}
}
