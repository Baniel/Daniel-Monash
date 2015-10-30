package com.example.slidingmenu.view;

import com.nineoldandroids.view.ViewHelper;

import android.R;
import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mScreenWidth;
	private int mMenuRightPadding = 50;
	private boolean once;
	private int mMenuWidth;
	private boolean isOpen;

	/**
	 * 
	 * 未使用自定义属性时，调用
	 * 
	 */
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	/*
	 * 设置子View的宽和高
	 */

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);

			mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
			mMenuWidth = mMenu.getLayoutParams().width;
			mContent.getLayoutParams().width = mScreenWidth;
			mWapper.getLayoutParams().width = mScreenWidth;
			once = true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	/**
	 * 通过设置偏移量，将Menu隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		super.onLayout(changed, l, t, r, b);

		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}

		this.scrollTo(mMenuWidth, 0);

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:

			// 隐藏在左边的宽度
			int scrollX = getScrollX();

			if (scrollX >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
			} else {
				this.smoothScrollTo(0, 0);
				isOpen = true;
			}

			return true;

		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 当使用了自定义了属性时，会调用此构造方法
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		// 获取定义的属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				com.example.assignment.R.styleable.SlidingMenu, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case com.example.assignment.R.styleable.SlidingMenu_rightPadding:
				mMenuRightPadding = a.getDimensionPixelSize(attr,
						(int) TypedValue.applyDimension(
								TypedValue.COMPLEX_UNIT_DIP, 50, context
										.getResources().getDisplayMetrics()));
				break;

			}
		}
		a.recycle();

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		mScreenWidth = outMetrics.widthPixels;

	}

	public SlidingMenu(Context context) {
		this(context, null);

	}

	/*
	 * 打开菜单
	 */
	public void openMenu() {
		if (isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = true;
	}

	/*
	 * 关闭菜单
	 */
	public void closeMenu() {
		if (!isOpen)
			return;
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen = false;

	}

	/*
	 * 切换菜单
	 */
	public void toggle() {
		if (isOpen) {
			closeMenu();
		} else {
			{
				openMenu();
			}
		}

	}

	/*
	 * 滚动发生时触发
	 */

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
		
		
		float scale= l * 1.0f / mMenuWidth; // 1~0
		
		// 调用属性动画，设置TranslationX
		
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
