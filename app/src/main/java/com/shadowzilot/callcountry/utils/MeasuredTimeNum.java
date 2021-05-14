package com.shadowzilot.callcountry.utils;

public class MeasuredTimeNum {
    private float mMeasuredWidth;
    private float mMeasuredHeight;
    private String mTimeNum;

    public MeasuredTimeNum(float _width, float _height, String _num) {
        mMeasuredWidth = _width;
        mMeasuredHeight = _height;
        mTimeNum = _num;
    }

    public float getMeasuredWidth() {
        return mMeasuredWidth;
    }

    public float getMeasuredHeight() {
        return mMeasuredHeight;
    }

    public String getTimeNum() {
        return mTimeNum;
    }
}
