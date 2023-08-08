package com.epam.rd.autotasks.figures;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

abstract class Figure{

    public abstract Point centroid();
    public abstract boolean isTheSame(Figure figure);
}
