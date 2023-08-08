package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private Point point;
    private double r;

    public Circle(Point point, double r) {
        this.point = point;
        this.r = r;
        if (r <= 0 || point == null) throw new IllegalArgumentException();
    }

    private static boolean isRelativelyEqual(double d1, double d2) {
        return d1 == d2 || 0.00001 > Math.abs(d1- d2) / Math.max(Math.abs(d1), Math.abs(d2));
    }

    @Override
    public Point centroid() {
        return this.point;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (figure.getClass() == this.getClass()) {
            return ((Circle) figure).point.isEqual(this.point) && isRelativelyEqual(((Circle) figure).r,this.r);
        }
        return false;
    }
}
