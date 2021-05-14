package com.shadowzilot.callcountry.utils;

public class Coordinate {
    private double X;
    private double Y;
    private double Angle;

    public Coordinate(double _x, double _y) {
        this.X = _x;
        this.Y = _y;
        this.Angle = 0;
    }
    public Coordinate(double _x, double _y, double _angle) {
        this.X = _x;
        this.Y = _y;
        this.Angle = (_angle * 180) / Math.PI;
        this.Angle -= 90;
    }

    public float getX() {
        return (float) this.X;
    }

    public float getY() {
        return (float) this.Y;
    }

    public double getAngle() {
        return this.Angle + 180.0;
    }
}
