package com.epam.rd.autotasks;

import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double D = b * b - 4 * a * c;

        if (D < 0) System.out.println("no roots");
        else if (D == 0) System.out.println(-b/ (2 * a));
        else System.out.println((-b + sqrt(D))/ (2 * a) + " " + (-b - sqrt(D))/ (2 * a));
    }

}
