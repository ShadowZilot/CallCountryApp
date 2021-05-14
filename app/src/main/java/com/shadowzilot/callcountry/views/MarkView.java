package com.shadowzilot.callcountry.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.shadowzilot.callcountry.R;

public class MarkView extends View {
    private final String LOG_TAG = this.getClass().getName();
    private final int DEFAULT_VIEW_HEIGHT = 100;
    private final int DEFAULT_VIEW_WIDTH = 100;
    private final float DEFAULT_START = 270f;
    private final int DEFAULT_COLOR = Color.parseColor("#000000");

    private int STROKE_WIDTH = 30;
    private int TEXT_SIZE = 30;
    private int MAX = 100;
    private float MARK = 0;
    private int TEXT_COLOR;

    private Paint textPaint;
    private Paint ringPaint;
    private Paint strokePaint;
    private Paint shadowPaint;
    private int[] gradients;
    private RectF oval;

    public MarkView(Context context) {
        this(context, null);
    }

    public MarkView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray attrs = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.MarkView,
                0, 0);
        try {
            STROKE_WIDTH = attrs.getDimensionPixelSize(R.styleable.MarkView_mv_stroke_width,
                    30);
            MAX = attrs.getInteger(R.styleable.MarkView_mv_max, 100);
            MARK = attrs.getFloat(R.styleable.MarkView_mv_mark, 0f);
            TEXT_SIZE = attrs.getDimensionPixelSize(R.styleable.MarkView_mv_text_size, 30);
            TEXT_COLOR = attrs.getColor(R.styleable.MarkView_mv_text_color, Color.BLACK);
        } finally {
            initPainters();
            attrs.recycle();
        }
    }

    private float getAngleMark() {
        return 360f / MAX * MARK;
    }

    private int getEndIndexOfGradient(float targetAngel) {
        float percentOf360 = ((targetAngel * 100f) / 360f) / 100f;
        return (int) (gradients.length * percentOf360);
    }

    private int getGradientColor(int r, int g) {
        return Color.argb(255, r, g, 10);
    }

    private void initPainters() {
        ringPaint = new Paint();
        ringPaint.setColor(Color.parseColor("#B5B8B1"));
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(STROKE_WIDTH);
        ringPaint.setAntiAlias(true);

        int r = 255;
        int g = 0;
        gradients = new int[354];
        for (int i = 0; i < gradients.length; i++) {
            Log.d(LOG_TAG, String.format("Red = %d, Green = %d", r, g));
            gradients[i] = getGradientColor(r, g);
            if (g >= 255) {
                r--;
            } else {
                g++;
            }
        }

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(STROKE_WIDTH);
        strokePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(TEXT_COLOR);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setAntiAlias(true);

        shadowPaint = new Paint();
        shadowPaint.setColor(Color.RED);
        shadowPaint.setStyle(Paint.Style.STROKE);
        shadowPaint.setStrokeWidth(2);
        strokePaint.setAntiAlias(true);

        oval = new RectF();
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

        oval.set(
                startPadding, topPadding,
                width - endPadding, height - bottomPadding
        );

        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawArc(oval, DEFAULT_START, 360, false, ringPaint);
        if (MARK < MAX) {
            float targetAngle = getAngleMark(); // Get target angle for draw correct gradient
            // Get index of end color for gradient
            int endIndexOfGradient = getEndIndexOfGradient(targetAngle);
            float deltaAngel = targetAngle / endIndexOfGradient;
            float startAngel = DEFAULT_START;

            for (int i = 0; i < endIndexOfGradient; i++) {
                strokePaint.setColor(gradients[i]);
                canvas.drawArc(oval, startAngel-(deltaAngel/5),
                        deltaAngel+(deltaAngel/5), false, strokePaint);
                startAngel += deltaAngel;
            }
        } else {
            strokePaint.setColor(gradients[gradients.length-1]);
            canvas.drawArc(oval, DEFAULT_START, 360f, false, strokePaint);
        }

        String text = String.valueOf((int) MARK);
        float textHeight = textPaint.descent() + textPaint.ascent();
        canvas.drawText(text,
                getWidth() / 2f - textPaint.measureText(text)/2,
                getHeight() / 2f - textHeight/2f, textPaint);
    }

    public void setMark(float newMark) {
        if (newMark <= MAX) {
            MARK = newMark;
        }
        invalidate();
    }
}
