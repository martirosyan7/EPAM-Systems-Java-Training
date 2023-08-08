package com.epam.rd.autotasks;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();
    private final double a;
    private final double b;
    private final double c;
    private String expected;

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;

    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[]{1, 3, 2, "-1 -2"}, new Object[]{1, 6, 5, "-1 -5"}, new Object[]{1, 1, -6, "2 -3"}, new Object[]{1, 1, -12, "3 -4"});
    }
    @Test
    public void twoRootTest() {
        boolean successful = false;
        try {
            double ex1 = Double.parseDouble(this.expected.split(" ")[0]);
            double ex2 = Double.parseDouble(this.expected.split(" ")[1]);
            String result = this.quadraticEquation.solve(a, b, c);
            double ax1 = Double.parseDouble(result.split(" ")[0]);
            double ax2 = Double.parseDouble(result.split(" ")[1]);
            boolean reverse = ex1 == ax2 && ex2 == ax1;
            boolean nonReverse = ex1 == ax1 && ex2 == ax2;
            successful = reverse || nonReverse;
        } catch (Exception e) {
            successful = false;
        }
        Assert.assertTrue(successful);

    }
}
