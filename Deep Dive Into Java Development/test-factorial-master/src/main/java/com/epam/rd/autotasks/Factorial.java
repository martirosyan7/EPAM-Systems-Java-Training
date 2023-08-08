package com.epam.rd.autotasks;

public class Factorial {
    public String factorial(String n) {
        if (Integer.parseInt(n) < 0 || n == null) throw new IllegalArgumentException();
        int num = 0;
        try {
            num = Integer.parseInt(n);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        if (num == 0) return "1";
        else if (num == 1) return "1";
        else return String.valueOf(num * Long.parseLong(factorial(String.valueOf(num - 1))));
    }
}
