package com.epam.rd.autocode.bstprettyprint;


import com.sun.source.tree.Tree;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public interface PrintableTree {

    void add(int i);
    String prettyPrint();

    static PrintableTree getInstance() {
        return new PrintableTreeImpl();
    }
}
