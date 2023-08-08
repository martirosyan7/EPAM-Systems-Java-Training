package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;
import static java.lang.Math.min;

class Triangle extends Figure {
    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        try {
            this.a = a;
            this.b = b;
            this.c = c;
            if (area() == 0 || !isValid()) throw new IllegalArgumentException();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isRelativelyEqual(double d1, double d2) {
        return 0.00001 > Math.abs(d1- d2) / Math.max(Math.abs(d1), Math.abs(d2));
    }

    public boolean isValid() {
        double AB = distance(a, b);
        double BC = distance(b, c);
        double AC = distance(a, c);
        double max = max(AB, max(BC, AC));
        double min = min(AB, min(BC, AC));
        double middle = AB + BC + AC - max - min;
        if (max >= min + middle || isRelativelyEqual(max, min + middle)) {
            return false;
        }
        return true;
    }

    public double distance(Point point1, Point point2) {
        return sqrt(pow(point2.getX() - point1.getX(), 2) + pow(point2.getY() - point1.getY(), 2));
    }

    public double area() {
        double AB = distance(a, b);
        double BC = distance(b, c);
        double AC = distance(a, c);
        double p = (AB + BC + AC) / 2;

        return sqrt(p * (p - AB) * (p - BC) * (p - AC));
    }


    @Override
    public Point centroid() {
        double centroidX = (this.a.getX() + this.b.getX() + this.c.getX())/3;
        double centroidY = (this.a.getY() + this.b.getY() + this.c.getY())/3;
        return new Point(centroidX, centroidY);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure.getClass() == this.getClass()) {
            int count = 0;
            Point[] points1 = {this.a, this.b, this.c};
            Point[] points2 = {((Triangle) figure).a, ((Triangle) figure).b, ((Triangle) figure).c};
            for (Point point1 : points1){
                for (Point point2 : points2) {
                    if (point1 == point2) {
                        count++;
                    }
                }
            }
            if (count == 3) return true;
        }
        return false;
    }
}
