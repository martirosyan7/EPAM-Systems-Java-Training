package com.epam.rd.autotasks.segments;

import static java.lang.Math.*;
import static java.lang.StrictMath.pow;


public class Segment {
    Point start;
    Point end;
    double gradient;
    double yIntercept;
    public Segment(Point start, Point end) {
        if ((start.getX() != end.getX()) || (start.getY() != end.getY())) {
            this.start = start;
            this.end = end;
            this.gradient = findGradient();
            this.yIntercept = findYIntercept();
        } else {
            throw new RuntimeException("Both X: " + start.getX() + " = " + end.getX() + " and Y: " + start.getY() + " = " + end.getY());
        }
    }

    double length() {
        double d = sqrt(pow(end.getX() - start.getX(), 2) + pow(end.getY() - start.getY(), 2));
        return d;
    }

    Point middle() {
        double midpointX = (start.getX() + end.getX()) / 2;
        double midpointY = (start.getY() + end.getY()) / 2;
        return new Point(midpointX, midpointY);
    }

    Point intersection(Segment another) {
        if (another.gradient == gradient) return null;
        double intersectionX = (another.yIntercept - yIntercept) / (gradient - another.gradient);
        double intersectionY = gradient * intersectionX + yIntercept;
        System.out.println(intersectionX);
        if (!isInDomain(intersectionX) || !another.isInDomain(intersectionX)) return null;
        return new Point(intersectionX, intersectionY);
    }

    private double findGradient() {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    private double findYIntercept() {
        return start.getY() - gradient * start.getX();
    }

    private boolean isInDomain(double x) {
        System.out.println(max(start.getX(),end.getX()) <= x && min(start.getX(),end.getX()) >= x);
        return max(start.getX(),end.getX()) >= x && min(start.getX(),end.getX()) <= x;
    }

}
