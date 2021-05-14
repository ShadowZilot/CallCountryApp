package com.shadowzilot.callcountry.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.shadowzilot.callcountry.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class WPGScoreTable extends View {
    private static final String sLogTag = WPGScoreTable.class.getSimpleName();
    private final static int sSideOffset = 10;
    private final static int sDefaultBackLinesColor = Color.LTGRAY;
    private final static int sDefaultCurrentScoreLineColor = Color.GREEN;
    private final static int sDefaultHighScoreLineColor = Color.YELLOW;
    private final static int sDefaultWidthLine = 6;
    private final static int sDefaultTextSize = 20;
    private final static float sDivisionPrice = 5f;
    private final static int sDefaultLineToLineLength = 30;

    private static boolean sIsScrolling = false;
    private static boolean sIsHighScoreInvisible = true;
    private static boolean isAllowingDrawInvisibleLine = false;

    private float mPreviousCameraPos = 0;

    private final int mBackLinesColor;
    private final int mCurrentScoreLineColor;
    private final int mHighScoreLineColor;
    private final float mWidthLine;
    private final int mTextSize;
    private final float mLineToLineLength;

    private float mCurrentScore;
    private float mHighScore;

    private Paint mBackLinesPaint;
    private Paint mCurrentScoreLinePaint;
    private Paint mHighScoreLinePaint;
    private Paint mBackTextPaint;
    private Paint mScoreTextPaint;

    private Path mLineDrawPath;

    public WPGScoreTable(Context _context) {
        this(_context, null);
    }

    public WPGScoreTable(Context _context, AttributeSet attributeSet) {
        super(_context, attributeSet);

        TypedArray attrs = getContext().getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.WPGScoreTable, 0, 0);

        try {
            mWidthLine = attrs.getDimensionPixelSize(R.styleable.WPGScoreTable_st_lineWidth,
                    sDefaultWidthLine);
            mBackLinesColor = attrs.getColor(R.styleable.WPGScoreTable_st_zrLineColor,
                    sDefaultBackLinesColor);
            mCurrentScoreLineColor = attrs.getColor(R.styleable.WPGScoreTable_st_scrLineColor,
                    sDefaultCurrentScoreLineColor);
            mHighScoreLineColor = attrs.getColor(R.styleable.WPGScoreTable_st_highLineColor,
                    sDefaultHighScoreLineColor);
            mTextSize = attrs.getDimensionPixelSize(R.styleable.WPGScoreTable_st_scrTextSize,
                    sDefaultTextSize);
            mLineToLineLength = attrs.getDimensionPixelSize(R.styleable.WPGScoreTable_st_lineToLineLength,
                    sDefaultLineToLineLength);

            mCurrentScore = attrs.getInteger(R.styleable.WPGScoreTable_st_Score, 0);
            mHighScore = attrs.getInteger(R.styleable.WPGScoreTable_st_highScore, 0);
        } finally {
            attrs.recycle();
            initPainters();
        }
    }

    @Override
    public void onMeasure(int measureWidthSpec, int measureHighSpec) {
        super.onMeasure(measureWidthSpec, measureHighSpec);
        mPreviousCameraPos = -(mLineToLineLength / sDivisionPrice) * 1.087f;
    }

    @Override
    public void onDraw(Canvas canvas) {
        // Calculate draw pos for current score line
        float lengths = (mCurrentScore / sDivisionPrice) * mLineToLineLength;
        float scoreDrawPos = (getHeight() - mLineToLineLength) - lengths;
        sIsHighScoreInvisible = determineVisibilityHighScore(getHeight(), scoreDrawPos);

        lengths = (mHighScore / sDivisionPrice) * mLineToLineLength;
        float highScoreDrawPos = (getHeight() - mLineToLineLength) - lengths;

        if (sIsScrolling) {
            canvas.translate(0, -mPreviousCameraPos);
        }

        int fillingBackScore = 0;
        int startPos = (int) (getHeight() - mLineToLineLength);
        for (int i = startPos; i >= -mLineToLineLength * 1000; i -= mLineToLineLength) {
            if (fillingBackScore % 10 == 0) {
                drawBackLine(canvas, i, String.valueOf(fillingBackScore));
            } else {
                drawBackLine(canvas, i, "");
            }
            fillingBackScore += (int) sDivisionPrice;
        }

        drawScoreLine(canvas, scoreDrawPos,
                String.valueOf((int) mCurrentScore), mCurrentScoreLinePaint);
        if (sIsHighScoreInvisible & isAllowingDrawInvisibleLine) {
            drawScoreLine(canvas,
                    scoreDrawPos - mLineToLineLength*6,
                    String.valueOf((int) mHighScore), mHighScoreLinePaint);
        }
        drawScoreLine(canvas, highScoreDrawPos,
                String.valueOf((int) mHighScore), mHighScoreLinePaint);
    }

    private void drawScoreLine(Canvas _canvas, float yMiddleLine,
                               String lineText, Paint _paint) {
        // Calculate width and height of text
        // For determine size of rect
        float textWidth = mScoreTextPaint.measureText(lineText) + 10;
        float textHeight = (mScoreTextPaint.ascent() + mScoreTextPaint.descent()) - 5;

        // Setting path
        mLineDrawPath.moveTo(sSideOffset, yMiddleLine);
        mLineDrawPath.rLineTo(0, -textHeight / 2);
        mLineDrawPath.rLineTo(textWidth, 0);
        mLineDrawPath.rLineTo(10, (textHeight / 2) + (mWidthLine / 2));
        mLineDrawPath.rLineTo((getWidth() - sSideOffset * 2 - textWidth - 10), 0);
        mLineDrawPath.rLineTo(0, -mWidthLine);
        mLineDrawPath.rLineTo(-(getWidth() - textWidth - sSideOffset * 3), 0);
        mLineDrawPath.rLineTo(-10, textHeight / 2);
        mLineDrawPath.rLineTo(-textWidth, 0);
        mLineDrawPath.rLineTo(0, -textHeight);
        // Draw and reset path
        // Also we draw text indicates score line
        _canvas.drawPath(mLineDrawPath, _paint);
        _canvas.drawText(lineText, (sSideOffset + (textWidth / 2)) - (textWidth - 10) / 2,
                yMiddleLine - textHeight / 2 - 2, mScoreTextPaint);
        mLineDrawPath.reset();
    }

    private void drawBackLine(Canvas _canvas, float yMiddleLine, String lineText) {
        // Setting path
        mLineDrawPath.moveTo(sSideOffset, yMiddleLine);
        mLineDrawPath.rLineTo(0, mWidthLine / 2);
        mLineDrawPath.rLineTo(getWidth() - sSideOffset * 2, 0);
        mLineDrawPath.rLineTo(0, -mWidthLine);
        mLineDrawPath.rLineTo(-(getWidth() - sSideOffset * 2), 0);
        mLineDrawPath.rLineTo(0, mWidthLine);
        // Prepare text to draw
        float textWidth = mBackTextPaint.measureText(lineText);
        _canvas.drawText(lineText, getWidth() - sSideOffset - textWidth,
                yMiddleLine - mWidthLine / 2, mBackTextPaint);

        // Next draw and reset path
        _canvas.drawPath(mLineDrawPath, mBackLinesPaint);
        mLineDrawPath.reset();
    }

    private void initPainters() {
        mBackLinesPaint = new Paint();
        mBackLinesPaint.setColor(mBackLinesColor);
        mBackLinesPaint.setAntiAlias(true);

        mCurrentScoreLinePaint = new Paint();
        mCurrentScoreLinePaint.setColor(mCurrentScoreLineColor);
        mCurrentScoreLinePaint.setAntiAlias(true);
        mCurrentScoreLinePaint.setPathEffect(new CornerPathEffect(8));

        mHighScoreLinePaint = new Paint();
        mHighScoreLinePaint.setColor(mHighScoreLineColor);
        mHighScoreLinePaint.setAntiAlias(true);
        mHighScoreLinePaint.setPathEffect(new CornerPathEffect(8));

        mScoreTextPaint = new Paint();
        mScoreTextPaint.setColor(Color.BLACK);
        mScoreTextPaint.setTextSize(mTextSize);
        mScoreTextPaint.setAntiAlias(true);

        mBackTextPaint = new Paint();
        mBackTextPaint.setColor(mBackLinesColor);
        mBackTextPaint.setTextSize(mTextSize);
        mBackTextPaint.setAntiAlias(true);

        mLineDrawPath = new Path();
    }

    public void drawHighScore() {
        // This method will be draw high score line
        // If it invisible now
        sIsScrolling = true;
        isAllowingDrawInvisibleLine = true;
        invalidate();
    }

    private boolean determineVisibilityHighScore(float _height, float _scoreDrawPos) {
        float lengths = (mHighScore / sDivisionPrice) * mLineToLineLength;
        float highScoreDrawPos = (_height - mLineToLineLength) - lengths;
        return _height < (Math.abs(highScoreDrawPos) - Math.abs(_scoreDrawPos));
    }

    public void setScore(float newScore) {
        mCurrentScore = newScore;
        sIsScrolling = false;
        invalidate();
    }

    public void setOffset(float cameraOffset) {
        mPreviousCameraPos = cameraOffset;
        sIsScrolling = true;
        invalidate();
    }

    public void setHighScore(float newHighScore) {
        sIsScrolling = false;
        mHighScore = newHighScore;
    }

    public float getEndScorePos(float score) {
        float lengths = (score / sDivisionPrice) * mLineToLineLength;
        return ((getHeight() - mLineToLineLength) - lengths) + mLineToLineLength * 3;
    }
}
