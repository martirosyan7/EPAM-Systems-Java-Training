package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorialRegularInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void bigNumbersTest() {
        assertEquals("1307674368000", factorial.factorial("15"));
    }

    @Test
    void zeroTest() {
        assertEquals("1", factorial.factorial("0"));
    }
}
