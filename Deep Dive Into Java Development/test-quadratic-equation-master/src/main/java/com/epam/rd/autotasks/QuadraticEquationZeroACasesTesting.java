package com.epam.rd.autotasks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {

    private final double a;
    private final double b;
    private final double c;

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[]{0, 3, 0}, new Object[]{0, 6, -5}, new Object[]{0, 1, 6}, new Object[]{0, 1, 2});
    }
    @Test (expected = IllegalArgumentException.class)
    public void zeroACaseTest() {
        quadraticEquation.solve(a, b, c);
    }

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
}
