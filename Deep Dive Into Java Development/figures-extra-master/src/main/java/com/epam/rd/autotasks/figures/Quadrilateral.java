package com.epam.rd.autotasks.figures;

import static java.lang.Math.*;

class Quadrilateral extends Figure {
    private Point a;
    private Point b;
    private Point c;
    private Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        try {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;


            if (area() == 0 || isComplex() || isPlain()) throw new IllegalArgumentException();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public double distance(Point point1, Point point2) {
        return sqrt(pow(point2.getX() - point1.getX(), 2) + pow(point2.getY() - point1.getY(), 2));
    }

    public boolean isPlain() {
        double d1 = distance(a, c);
        double d2 = distance(b, d);
        double AB = distance(a, b);
        double BC = distance(b, c);
        double CD = distance(c, d);
        double AD = distance(a, d);
        double maxD = max(max(AB, BC), max(CD, AD));
        return !(d1 + d2 > AB + CD && d1 + d2 > BC + AD);
    }

    public double area() {
        Triangle triangle1 = new Triangle(a, b, d);
        Triangle triangle2 = new Triangle(b, d, c);
        return triangle1.area() + triangle2.area();
    }

    public double alternativeArea() {
        Triangle triangle1 = new Triangle(a, b, c);
        Triangle triangle2 = new Triangle(a, c, d);
        return triangle1.area() + triangle2.area();
    }

    private static boolean isRelativelyEqual(double d1, double d2) {
        return d1 == d2 || 0.00001 > Math.abs(d1- d2) / Math.max(Math.abs(d1), Math.abs(d2));
    }

    public boolean isComplex() {
        return !isRelativelyEqual(this.area(), this.alternativeArea());
    }

    @Override
    public Point centroid() {
//        double centroidX = (this.a.getX() + this.b.getX() + this.c.getX() + this.d.getX())/4.0;
//        double centroidY = (this.a.getY() + this.b.getY() + this.c.getY() + this.d.getY())/4.0;
//        System.out.println(String.format("%f %f %f %f %f", a.getX(), b.getX(), c.getX(), d.getX(), centroidX));
//        System.out.println(String.format("%f %f %f %f %f", a.getY(), b.getY(), c.getY(), d.getY(), centroidY));
//        return new Point(centroidX, centroidY);

        Point centroid1 = (new Triangle(a, b, c)).centroid();
        Point centroid2 = (new Triangle(a, d, c)).centroid();
        Point centroid3 = (new Triangle(d, b, c)).centroid();
        Point centroid4 = (new Triangle(a, b, d)).centroid();
        double k1 = (centroid1.getY() - centroid2.getY()) / (centroid1.getX() - centroid2.getX());
        double k2 = (centroid3.getY() - centroid4.getY()) / (centroid3.getX() - centroid4.getX());
        double b1 = centroid1.getY() - k1 * centroid1.getX();
        double b2 = centroid3.getY() - k2 * centroid3.getX();
        double cX = (b2 - b1) / (k1 - k2);
        double cY = cX * k1 + b1;
        return new Point(cX, cY);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure.getClass() == this.getClass()) {
            int count = 0;
            Point[] points1 = {this.a, this.b, this.c, this.d};
            Point[] points2 = {((Quadrilateral) figure).a, ((Quadrilateral) figure).b, ((Quadrilateral) figure).c, ((Quadrilateral) figure).d};
            for (Point point1 : points1){
                for (Point point2 : points2) {
                    if (point1.isEqual(point2)) {
                        count++;
                    }
                }
            }
            if (count == 4) return true;
        }
        return false;
    }
}