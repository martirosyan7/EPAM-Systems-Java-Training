package com.epam.rd.autotasks.figures;

abstract class Figure{

    public abstract double area();

    public abstract String pointsToString();

    public String toString() {
        return this.getClass().getSimpleName() + "[" + pointsToString() + "]";
    }

    public abstract Point leftmostPoint();

    public abstract double distance(Point a, Point b);

    public Point leftestPoint(Point point1, Point point2) {
        return (point1.getX() < point2.getX()) ? point1 : point2;
    }
}
