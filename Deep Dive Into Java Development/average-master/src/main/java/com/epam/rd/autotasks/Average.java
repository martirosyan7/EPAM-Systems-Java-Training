package com.epam.rd.autotasks;

import java.util.Scanner;

public class Average {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        String input = scanner.nextLine();
        String[] arr = input.split(" ");
        for (String a: arr) {
            n += Integer.parseInt(a);
        }
        n /= arr.length - 1;
        System.out.println(n);

    }

}
