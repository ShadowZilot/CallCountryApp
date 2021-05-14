package com.shadowzilot.callcountry.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.shadowzilot.callcountry.R;
import com.shadowzilot.callcountry.utils.Coordinate;
import com.shadowzilot.callcountry.utils.WorldPartClicked;

import java.util.ArrayList;


public class WorldPartButton extends View {
    private static final String LOG_TAG = WorldPartButton.class.getSimpleName();

    private static final int DEFAULT_VIEW_WIDTH = 150;
    private static final int DEFAULT_VIEW_HEIGHT = 150;

    private static final float START_MEASURE_ANGEL = -3.12414f;
    private static final float DELTA_SECTOR_ANGEL = 1.0122611111f;
    private static final float DELTA_SPACING_ANGEL = 0.0349055556f;

    private static final float START_SECTOR_POS = 241f;
    private static final int DEFAULT_TEXT_SIZE = 24;
    private static final int DEFAULT_COLOR = Color.LTGRAY;
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_STROKE_WIDTH = 20;

    private int mTouchedX = 0;
    private int mTouchedY = 0;

    private WorldPartClicked mListener;

    private float mLitWidth;
    private float mLitHeight;

    private ArrayList<ArrayList<Coordinate>> mListOfBounds = new ArrayList();
    private ArrayList<ArrayList<Coordinate>> mListTextPoints = new ArrayList<>();

    private int mTextSize;
    private int[] mButtonColors;
    private int mTextColor;
    private String[] mTextArray;
    private int mStrokeWidth;

    private Paint mTextPaint;
    private Paint mButtonPaint;
    private Paint mDividerPaint;
    private Paint mDebugPointPaint;

    private Rect mTextBoundRect = new Rect();
    private RectF mButtonRect;

    public WorldPartButton(Context context) {
        this(context, null);
    }

    public WorldPartButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        TypedArray attrs = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.WorldPartButton,
                0, 0);

        try {
            mTextSize = (int) attrs.getDimension(R.styleable.WorldPartButton_wb_text_size,
                    DEFAULT_TEXT_SIZE);
            int idColors = attrs.getResourceId(R.styleable.WorldPartButton_wb_button_colors,
                    DEFAULT_COLOR);
            mButtonColors = context.getResources().getIntArray(idColors);
            mTextColor = attrs.getColor(R.styleable.WorldPartButton_wb_text_color,
                    DEFAULT_TEXT_COLOR);
            int idTextArray = attrs.getResourceId(R.styleable.WorldPartButton_wb_text_array,
                    0);
            mTextArray = context.getResources().getStringArray(idTextArray);
            mStrokeWidth = attrs.getDimensionPixelSize(R.styleable.WorldPartButton_wb_stroke_width,
                    DEFAULT_STROKE_WIDTH);
        } finally {
            initPainters();
            attrs.recycle();
        }
    }

    private void initPainters() {
        mButtonPaint = new Paint();
        mButtonPaint.setColor(mButtonColors[0]);
        mButtonPaint.setStyle(Paint.Style.STROKE);
        mButtonPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);

        mDividerPaint = new Paint();
        mDividerPaint.setColor(Color.WHITE);
        mDividerPaint.setStyle(Paint.Style.STROKE);
        mDividerPaint.setStrokeWidth(mStrokeWidth);

        mDebugPointPaint = new Paint();
        mDebugPointPaint.setStrokeWidth(3f);
        mDebugPointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mDebugPointPaint.setColor(Color.BLACK);

        mButtonRect = new RectF();
    }

    public void setOnWorldPartClickListener(WorldPartClicked _clickListener) {
        mListener = _clickListener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int customWidth = DEFAULT_VIEW_WIDTH + getPaddingStart() + getPaddingEnd();
        int customHeight = DEFAULT_VIEW_HEIGHT + getPaddingTop() + getPaddingBottom();

        customWidth = resolveSize(customWidth, widthMeasureSpec);
        customHeight = resolveSize(customHeight, heightMeasureSpec);
        if (mListOfBounds.size() < 6) {
            setSectorsBounds(customWidth, customHeight);
            setPointsOfText(customWidth/2f);
        }
        setMeasuredDimension(customWidth, customHeight);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        float nextStartAngle = START_SECTOR_POS;

        mButtonRect.set(mStrokeWidth / 2, mStrokeWidth / 2,
                width - (mStrokeWidth / 2), height - (mStrokeWidth / 2));

        for (int i = 0; i < 6; i++) {
            mButtonPaint.setColor(mButtonColors[i]);
            canvas.drawArc(mButtonRect, nextStartAngle, 58f, false, mButtonPaint);
            canvas.drawArc(mButtonRect, nextStartAngle + 58f, 2f,
                    false, mDividerPaint);
            nextStartAngle += 60;
        }

        float tmpX;
        float tmpY;

        mTextPaint.setColor(Color.BLACK);

        for (int j = 0; j < mListTextPoints.size()/2; j++) {
            for (int i = 0; i < mListTextPoints.get(j).size(); i++) {
                canvas.save();

                double angle = mListTextPoints.get(j).get(i).getAngle();

                tmpX = mListTextPoints.get(j).get(i).getX();
                tmpY = mListTextPoints.get(j).get(i).getY();
                char tmpChar = mTextArray[j].charAt(i);

                Log.d(LOG_TAG, String.format("[%1c] Angle=%2f, X=%3f, Y=%3f", tmpChar,
                        angle, tmpX, tmpY));

                mTextPaint.getTextBounds(String.valueOf(tmpChar),
                        0, 1, mTextBoundRect);
                mLitWidth = mTextPaint.measureText(String.valueOf(tmpChar));
                mLitHeight = mTextBoundRect.height();

                canvas.rotate((float) angle, tmpX, tmpY);
                canvas.drawText(
                        String.valueOf(tmpChar),
                        tmpX-mLitWidth/2f,
                        tmpY+mLitHeight/2f,
                        mTextPaint
                );
                canvas.restore();
            }
        }

        mTextPaint.setColor(Color.WHITE);

        for (int j = mListTextPoints.size()-1; j >= 3; j--) {
            for (int i = 0; i < mListTextPoints.get(j).size(); i++) {
                canvas.save();

                double angle = mListTextPoints.get(j).get(i).getAngle() - 180.0;

                tmpX = mListTextPoints.get(j).get(i).getX();
                tmpY = mListTextPoints.get(j).get(i).getY();
                char tmpChar = mTextArray[j].charAt((mTextArray[j].length()-1)-i);

                Log.d(LOG_TAG, String.format("[%1c] Angle=%2f, X=%3f, Y=%3f", tmpChar,
                        angle, tmpX, tmpY));

                mTextPaint.getTextBounds(String.valueOf(tmpChar),
                        0, 1, mTextBoundRect);
                mLitWidth = mTextPaint.measureText(String.valueOf(tmpChar));
                mLitHeight = mTextBoundRect.height();

                canvas.rotate((float) angle, tmpX, tmpY);
                canvas.drawText(
                        String.valueOf(tmpChar),
                        tmpX-mLitWidth/2f,
                        tmpY+mLitHeight/2f,
                        mTextPaint
                );
                canvas.restore();
            }
        }


        //Draw points of text for debugging
        /*for (int j = 0; j < mListTextPoints.size(); j++) {
            for (int i = 0; i < mListTextPoints.get(j).size(); i++) {
                canvas.drawCircle(
                        mListTextPoints.get(j).get(i).getX(),
                        mListTextPoints.get(j).get(i).getY(),
                        5f,
                        mDebugPointPaint
                );
            }
        }*/

        // Draw points of sectors bounds for debugging
        /*for (int j = 0; j < mListOfBounds.size(); j++) {
            for (int i = 0; i < mListOfBounds.get(j).size(); i++) {
                float x = mListOfBounds.get(j).get(i).getX();
                float y = mListOfBounds.get(j).get(i).getY();
                canvas.drawCircle(x, y, 5f, mDebugPointPaint);
            }
        }*/
    }

    private void setSectorsBounds(int viewWidth, int viewHeight) {
        //TODO(Remake this func, this works WRONG!!!)
        float tmpX;
        float tmpY;

        // Calculated radius of internal circle
        float tmp_start_pos = (float) (4.20611);
        for (int j = 0; j < 6; j++) {
            // In follow calculation we suppose that viewWidth == viewHeight
            int radius = viewHeight / 2 - mStrokeWidth - getPaddingTop();

            float sectorPos = tmp_start_pos;
            float pinEndPoint = sectorPos + 1.01226f;

            mListOfBounds.add(new ArrayList<>());
            for (int l = 0; l < 11; l++) {
                tmpX = (float) ((radius * Math.cos(sectorPos)) + viewWidth / 2);
                tmpY = (float) ((radius * Math.sin(sectorPos)) + viewHeight / 2);

                mListOfBounds.get(mListOfBounds.size() - 1).add(new Coordinate(tmpX, tmpY));
                sectorPos += (pinEndPoint - tmp_start_pos) / 11;
            }
            sectorPos = tmp_start_pos;
            radius += mStrokeWidth;
            for (int l = 0; l < 11; l++) {
                tmpX = (float) ((radius * Math.cos(sectorPos)) + viewWidth / 2);
                tmpY = (float) ((radius * Math.sin(sectorPos)) + viewHeight / 2);

                mListOfBounds.get(mListOfBounds.size() - 1).add(new Coordinate(tmpX, tmpY));
                sectorPos += (pinEndPoint - tmp_start_pos) / 11;
            }
            tmp_start_pos = pinEndPoint + 0.034f;
        }
    }

    private void setPointsOfText(float radius) {
        // Temporary vars will used in coordinate calculations
        double tmpX;
        double tmpY;
        // Init angels vars
        float startSectorAngel = START_MEASURE_ANGEL;
        float endSectorAngel = startSectorAngel + DELTA_SECTOR_ANGEL;
        float currentCalculationAngel;
        float deltaAngel;
        // Init coordinate delta, needs for align points with center of circle
        float radiusDelta = mStrokeWidth/2f;
        radius -= radiusDelta;

        for (int j = 0; j < mTextArray.length; j++) {
            deltaAngel = DELTA_SECTOR_ANGEL / mTextArray[j].length();
            float startOffset = ((endSectorAngel - startSectorAngel)
                    - (deltaAngel*(mTextArray[j].length()-1)))/2;

            currentCalculationAngel = startSectorAngel + startOffset;

            mListTextPoints.add(new ArrayList<>());
            for (int i = 0; i < mTextArray[j].length(); i++) {
                // Plus radius for align with circle
                tmpX = (Math.cos(currentCalculationAngel) * radius) + radius+radiusDelta;
                tmpY = (Math.sin(currentCalculationAngel) * radius) + radius+radiusDelta;

                mListTextPoints.get(mListTextPoints.size()-1).add(new Coordinate(tmpX, tmpY,
                        currentCalculationAngel));
                currentCalculationAngel += deltaAngel;
            }

            startSectorAngel = endSectorAngel + DELTA_SPACING_ANGEL;
            endSectorAngel = startSectorAngel + DELTA_SECTOR_ANGEL;
        }
    }

    private int getLitSum() {
        int sum = 0;
        for(String s: mTextArray) {
            sum += s.length();
        }
        return sum;
    }

    private float getMaxBoundsX(int index) {
        float max = mListOfBounds.get(index).get(0).getX();
        for (int i = 1; i < mListOfBounds.get(index).size(); i++) {
            if (mListOfBounds.get(index).get(i).getX() > max) {
                max = mListOfBounds.get(index).get(i).getX();
            }
        }
        return max;
    }

    private float getMinBoundsX(int index) {
        float min = mListOfBounds.get(index).get(0).getX();
        for (int i = 1; i < mListOfBounds.get(index).size(); i++) {
            if (mListOfBounds.get(index).get(i).getX() < min) {
                min = mListOfBounds.get(index).get(i).getX();
            }
        }
        return min;
    }

    private float getMaxBoundsY(int index) {
        float max = mListOfBounds.get(index).get(0).getY();
        for (int i = 1; i < mListOfBounds.get(index).size(); i++) {
            if (mListOfBounds.get(index).get(i).getY() > max) {
                max = mListOfBounds.get(index).get(i).getY();
            }
        }
        return max;
    }

    private float getMinBoundsY(int index) {
        float min = mListOfBounds.get(index).get(0).getY();
        for (int i = 1; i < mListOfBounds.get(index).size(); i++) {
            if (mListOfBounds.get(index).get(i).getY() < min) {
                min = mListOfBounds.get(index).get(i).getY();
            }
        }
        return min;
    }

    private float[] getYRange(int forX, int index) {
        float[] result = new float[2];

        ArrayList<Coordinate> coordinates = mListOfBounds.get(index);

        result[0] = coordinates.get(forX).getY();
        result[1] = coordinates.get(forX + 11).getY();
        if (result[0] > result[1]) {
            float tmp = result[0];
            result[0] = result[1];
            result[1] = tmp;
        }
        return result;
    }

    private float[] getXRange(int forY, int index) {
        float[] result = new float[2];

        ArrayList<Coordinate> coordinates = mListOfBounds.get(index);

        result[0] = coordinates.get(forY).getX();
        result[1] = coordinates.get(forY + 11).getX();
        if (result[0] > result[1]) {
            float tmp = result[0];
            result[0] = result[1];
            result[1] = tmp;
        }
        return result;
    }

    private boolean isCheckYAlgorithm(int index) throws Exception {
        float deltaX = getMaxBoundsX(index) - getMinBoundsX(index);
        float deltaY = getMaxBoundsY(index) - getMinBoundsY(index);
        if (deltaX > deltaY) {
            return true;
        } else if (deltaY > deltaX) {
            return false;
        }
        throw new Exception("Error illegal deltaX and deltaY");
    }

    private int checkSectorBounds(int touchX, int touchY) throws Exception {
        for (int i = 0; i < mListOfBounds.size(); i++) {

            if (isCheckYAlgorithm(i)) {
                if (touchX >= getMinBoundsX(i) && touchX <= getMaxBoundsX(i)) {
                    for (int j = 0; j < mListOfBounds.get(i).size() / 2; j++) {

                        float[] yRange = getYRange(j, i);
                        if (touchY >= yRange[0] && touchY <= yRange[1]) {
                            return i;
                        }
                    }
                }
            } else {
                if (touchY >= getMinBoundsY(i) && touchY <= getMaxBoundsY(i)) {
                    for (int j = 0; j < mListOfBounds.get(i).size() / 2; j++) {

                        float[] xRange = getXRange(j, i);
                        if (touchX >= xRange[0] && touchX <= xRange[1]) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            try {
                mListener.onWorldPartSelected(checkSectorBounds((int) event.getX(),
                        (int) event.getY()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onTouchEvent(event);
        return true;
    }
}
