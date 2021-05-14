package com.shadowzilot.callcountry.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.shadowzilot.callcountry.R;
import com.shadowzilot.callcountry.utils.MeasuredTimeNum;

import java.util.ArrayList;

public class TimerView extends View {
    private final static int DEFAULT_WIDTH = 150;
    private final static int DEFAULT_HEIGHT = 150;

    private final static int DEFAULT_TEXT_SIZE = 20;
    private final static int DEFAULT_STROKE_WIDTH = 30;
    private final static int DEFAULT_RING_WIDTH = 15;
    private final static int DEFAULT_STROKE_COLOR = Color.parseColor("#7FFFD4");
    private final static int DEFAULT_RING_COLOR = Color.parseColor("#B5B8B1");

    private int mMaxTime;
    private float mCurrentTime;

    private ArrayList<MeasuredTimeNum> mListOfMeasured = new ArrayList<MeasuredTimeNum>();

    private float mLitWidth;
    private int mLitHeight;

    private int mTextSize;
    private int mStrokeWidth;
    private int mRingWidth;
    private int mStrokeColor;
    private int mRingColor;

    private Paint mTextPaint;
    private Paint mStrokePaint;
    private Paint mRingPaint;
    private RectF mMainRect;
    private Rect mTextBoundRect = new Rect();

    public TimerView(Context context) {
        this(context, null);
    }

    public TimerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray attrs = context.getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.TimerView, 0, 0);
        try {
            mTextSize = attrs.getDimensionPixelSize(R.styleable.TimerView_tv_textSize,
                    DEFAULT_TEXT_SIZE);
            mRingWidth = attrs.getDimensionPixelSize(R.styleable.TimerView_tv_ringWidth,
                    DEFAULT_RING_WIDTH);
            mStrokeWidth = attrs.getDimensionPixelSize(R.styleable.TimerView_tv_strokeWidth,
                    DEFAULT_STROKE_WIDTH);
            mRingColor = attrs.getColor(R.styleable.TimerView_tv_ringColor, DEFAULT_RING_COLOR);
            mStrokeColor = attrs.getColor(R.styleable.TimerView_tv_strokeColor,
                    DEFAULT_STROKE_COLOR);
        } finally {
            attrs.recycle();
            initPainters();
        }
    }

    private void initPainters() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);

        mRingPaint = new Paint();
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStrokeWidth(mRingWidth);
        mRingPaint.setStyle(Paint.Style.STROKE);

        mStrokePaint = new Paint();
        mStrokePaint.setColor(mStrokeColor);
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setStyle(Paint.Style.STROKE);

        mMainRect = new RectF();
        updateMeasuredLitList();
    }

    private double getAnglePercent() {
        return 360.0 * (((mCurrentTime * 100.0) / mMaxTime) / 100.0);
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHeightSpec) {
        int customWidth = DEFAULT_WIDTH + getPaddingStart() + getPaddingEnd();
        int customHeight = DEFAULT_RING_COLOR + getPaddingBottom() + getPaddingTop();

        customWidth = resolveSize(customWidth, measureWidthSpec);
        customHeight = resolveSize(customHeight, measureHeightSpec);

        mMainRect.set(mStrokeWidth/2f, mStrokeWidth/2f,
                customWidth-mStrokeWidth/2f, customHeight-mStrokeWidth/2f);

        setMeasuredDimension(customWidth, customHeight);
    }

    private int getRoundedIndex() {
        return (int) (Math.ceil(mCurrentTime));
    }

    @Override
    public void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        mLitWidth = mListOfMeasured.get(getRoundedIndex()).getMeasuredWidth();
        mLitHeight = (int) mListOfMeasured.get(getRoundedIndex()).getMeasuredHeight();

        canvas.drawText(mListOfMeasured.get(getRoundedIndex()).getTimeNum(),
                (width/2f)-(mLitWidth/2f), (height/2f)+(mLitHeight/2f),
                mTextPaint);

        canvas.drawArc(mMainRect, 270f, 360f, false, mRingPaint);
        canvas.drawArc(mMainRect, 270f, (float) getAnglePercent(),
                false, mStrokePaint);
    }

    private void updateMeasuredLitList() {
        mListOfMeasured.clear();
        for (int i = 0; i <= mMaxTime; i++) {
            String tmp = String.valueOf(i);

            mTextPaint.getTextBounds(tmp,
                    0, 1, mTextBoundRect);
            mLitWidth = mTextPaint.measureText(tmp);
            mLitHeight = mTextBoundRect.height();

            mListOfMeasured.add(new MeasuredTimeNum(mLitWidth, mLitHeight, tmp));
        }
        mLitWidth = 0;
        mLitHeight = 0;
    }

    public void setMaxTime(int newMaxTime) {
        mMaxTime = newMaxTime;
        updateMeasuredLitList();
    }

    public void setCurrentTime(float newTime) {
        if (newTime <= mMaxTime && newTime >= 0) {
            mCurrentTime = newTime;
            invalidate();
        }
    }
}
