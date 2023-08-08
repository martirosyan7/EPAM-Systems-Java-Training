package com.epam.rd.autotasks;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private final double a;
    private final double b;
    private final double c;
    private final double expected;

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[]{1, 2, 1, -1}, new Object[]{1, 4, 4, -2}, new Object[]{1, 6, 9, -3}, new Object[]{1, 8, 16, -4});
    }

    @Test
    public void singleRootCase() {
        Assert.assertEquals(String.valueOf(this.expected), this.quadraticEquation.solve(this.a, this.b, this.c));
    }
}
