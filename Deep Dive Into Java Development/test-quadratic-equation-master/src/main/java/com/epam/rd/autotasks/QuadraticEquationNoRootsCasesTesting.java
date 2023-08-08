package com.epam.rd.autotasks;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QuadraticEquationNoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private double a;
    private double b;
    private double c;

    public QuadraticEquationNoRootsCasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[]{1, 1, 1}, new Object[]{2, 2, 2}, new Object[]{3, 3, 3}, new Object[]{4, 4, 4});
    }

    @Test
    public void testNoRootsCase() {
        this.a = 1.0D;
        this.b = 1.0D;
        this.c = 1.0D;
        Assert.assertEquals("no roots", this.quadraticEquation.solve(this.a, this.b, this.c));
    }
}
