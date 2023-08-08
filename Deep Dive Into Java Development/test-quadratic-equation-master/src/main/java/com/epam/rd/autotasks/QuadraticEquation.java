package com.epam.rd.autotasks;

public class QuadraticEquation {
    public QuadraticEquation() {
    }

    public String solve(double a, double b, double c) {
        double D = Math.pow(b, 2.0D) - 4.0D * a * c;
        if (a == 0) throw new IllegalArgumentException();
        if (D < 0.0D) {
            return "no roots";
        } else if (D > 0.0D) {
            double x1 = (-b - Math.sqrt(D)) / (2.0D * a);
            double x2 = (-b + Math.sqrt(D)) / (2.0D * a);
            return x1 + " " + x2;
        } else {
            return String.valueOf(-b / (2.0D * a));
        }
    }
}
