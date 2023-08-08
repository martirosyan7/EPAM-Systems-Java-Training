package com.epam.rd.autotasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactorialBadInputTesting {

    Factorial factorial = new Factorial();

    @Test
    void testNullInput(){
        assertThrows(IllegalArgumentException.class, () -> {factorial.factorial(null);} );
    }

    @Test
    void testNegativeInput(){
        assertThrows(IllegalArgumentException.class, () -> {factorial.factorial("-5");} );
    }

    @Test
    void testFractionalInput(){
        assertThrows(IllegalArgumentException.class, () -> {factorial.factorial("3.2");} );
    }

    @Test
    void testNonDigitalInput(){
        assertThrows(IllegalArgumentException.class, () -> {factorial.factorial("nonDigitalInput");} );
    }


}
