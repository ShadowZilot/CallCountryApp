package com.shadowzilot.callcountry.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.shadowzilot.callcountry.R;

public class LearningProgress extends View {

    private static final int DEFAULT_VIEW_WIDTH = 250;
    private static final int DEFAULT_VIEW_HEIGHT = 20;

    private static final int DEFAULT_BACK_LINE_COLOR = Color.LTGRAY;
    private static final int DEFAULT_LINE_COLOR = Color.GREEN;
    private static final int DEFAULT_PROGRESS = 0;
    private static final int DEFAULT_CORNER_SIZE = 10;
    private static final int DEFAULT_MAX = 205;

    private int mBackLineColor;
    private int mLineColor;
    private float mProgress;
    private float mMax;
    private int mCornerSize;

    private Paint mBackLinePaint;
    private Paint mLinePaint;

    private RectF mBackLineRect;
    private RectF mLineRect;

    public LearningProgress(Context context) {
        this(context, null);
    }

    public LearningProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray attrs = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.LearningProgress,
                0, 0);

        try {
            mBackLineColor = attrs.getColor(R.styleable.LearningProgress_lp_back_line_color,
                    DEFAULT_BACK_LINE_COLOR);
            mLineColor = attrs.getColor(R.styleable.LearningProgress_lp_line_color,
                    DEFAULT_LINE_COLOR);
            mProgress = attrs.getFloat(R.styleable.LearningProgress_lp_progress,
                    DEFAULT_PROGRESS);
            mMax = attrs.getFloat(R.styleable.LearningProgress_lp_max, DEFAULT_MAX);
            mCornerSize = attrs.getDimensionPixelSize(R.styleable.LearningProgress_lp_corner_size,
                    DEFAULT_CORNER_SIZE);
        } finally {
            attrs.recycle();
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int startPadding = getPaddingStart();
        int endPadding = getPaddingEnd();
        int topPadding = getPaddingTop();
        int bottomPadding = getPaddingBottom();

        int width = DEFAULT_VIEW_WIDTH + startPadding + endPadding;
        width = resolveSize(width, widthMeasureSpec);

        int height = DEFAULT_VIEW_HEIGHT + topPadding + bottomPadding;
        height = resolveSize(height, heightMeasureSpec);

        setMeasuredDimension(width, height);
        initPainters(height, width);
    }

    private void initPainters(int strokeWidth, int width) {
        mBackLinePaint = new Paint();
        mBackLinePaint.setColor(mBackLineColor);
        mBackLinePaint.setStrokeWidth(strokeWidth);
        mBackLinePaint.setStyle(Paint.Style.FILL);
        mBackLinePaint.setAntiAlias(true);

        mLinePaint = new Paint();
        mLinePaint.setColor(mLineColor);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(strokeWidth);
        mLinePaint.setAntiAlias(true);

        mBackLineRect = new RectF(0, 0,
                width, strokeWidth);

        mLineRect = new RectF();
    }

    @Override
    public void onDraw(Canvas canvas) {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        canvas.drawRoundRect(mBackLineRect, mCornerSize, mCornerSize, mBackLinePaint);

        float progressPercent = (mProgress * 100) / mMax;
        float lineLen = width * (progressPercent / 100);

        mLineRect.set(0, 0, lineLen, height);
        canvas.drawRoundRect(mLineRect, mCornerSize, mCornerSize, mLinePaint);
    }

    public void setProgress(float newProgress) {
        this.mProgress = newProgress;
        invalidate();
    }

    public void setMax(float mMax) {
        this.mMax = mMax;
        invalidate();
    }
}
