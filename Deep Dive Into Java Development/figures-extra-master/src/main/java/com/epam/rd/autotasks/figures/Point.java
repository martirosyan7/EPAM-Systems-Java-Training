package com.epam.rd.autotasks.figures;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    private static boolean isRelativelyEqual(double d1, double d2) {
        return d1 == d2 || 0.00001 > Math.abs(d1- d2) / Math.max(Math.abs(d1), Math.abs(d2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isEqual(Point point) {
        return isRelativelyEqual(this.x, point.x) && isRelativelyEqual(this.y, point.y);
    }

}
