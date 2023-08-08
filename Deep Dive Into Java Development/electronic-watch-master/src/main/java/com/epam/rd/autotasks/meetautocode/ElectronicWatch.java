package com.epam.rd.autotasks.meetautocode;

import java.util.Scanner;

public class ElectronicWatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();
        String p1 = String.valueOf(seconds % 60);
        int p2 = seconds / 60;
        String p3 = String.valueOf(p2 % 60);
        p2 = (p2 / 60) % 24;

        if (Integer.parseInt(p3) / 10 == 0) {
            p3 = "0" + p3;
        }
        if (Integer.parseInt(p1) / 10 == 0) {
            p1 = "0" + p1;
        }

        System.out.println( p2 + ":" + p3 + ":" + p1);

    }
}
