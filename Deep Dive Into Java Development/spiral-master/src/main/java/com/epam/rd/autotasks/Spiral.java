package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int d = 1, r = 0, c = 1, i = 0, j = 0;
        int[][] arr = new int[rows][columns];
        while (c <= rows * columns) {
            if (i != -1 && j != -1 && j != rows && i != columns && arr[j][i] == 0) {
                arr[j][i] = c;
                c++;
                if (r % 2 == 0) i += d;
                else j += d;

            } else {
                r += 1;
                if (r % 2 == 0) {
                    d = -d;
                    i += d;
                } else {
                    i -= d;
                }
                j += d;
            }
        }

        return arr;
    }
}
