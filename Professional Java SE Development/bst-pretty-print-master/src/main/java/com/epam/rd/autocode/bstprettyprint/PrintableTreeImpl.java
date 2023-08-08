package com.epam.rd.autocode.bstprettyprint;


import java.io.IOException;

public class PrintableTreeImpl implements PrintableTree {

    String value;
    PrintableTreeImpl left, right;

    @Override
    public void add(int v) {
        if (value != null && Integer.parseInt(value) == v) return;
        if (value == null) {
            value = String.valueOf(v);
            return;
        }
        if (v > Integer.parseInt(value)) {
            if (left == null) {
                left = new PrintableTreeImpl();
            }
            left.add(v);
        } else {
            if (right == null) {
                right = new PrintableTreeImpl();
            }
            right.add(v);
        }
    }

    @Override
    public String prettyPrint() {
        String indent = "";
        for (int i = 0; i < value.length(); i++) {
            indent += " ";
        }
        String result = "";
        if (right != null) {
            try {
                result += right.getBranch(true, indent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            result += getSelfValue();
            if (right != null && left != null) result += "┤";
            else if (right != null) result += "┘";
            else if (left != null) result += "┐";
            result += "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (left != null) {
            try {
                result += left.getBranch(false, indent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    private String getSelfValue() throws IOException {
        if (value == null) {
            return "<null>";
        } else {
            return value;
        }
    }

    private String getBranch(boolean isRight, String indent) throws IOException {
        String newIndent = "";
        for (int i = 0; i < value.length(); i++) {
            newIndent += " ";
        }
        String result = "";
        if (right != null) {
            result += right.getBranch(true, indent + (isRight ? newIndent + " " : "│" + newIndent));
        }
        result += indent;
        if (isRight) {
            result += "┌";
        } else {
            result += "└";
        }
//        result += "----- ";
        result += getSelfValue();
        if (right != null && left != null) result += "┤";
        else if (right != null) result += "┘";
        else if (left != null) result += "┐";
        result += "\n";
        if (left != null) {
            result += left.getBranch(false, indent + (isRight ? "│" + newIndent : newIndent + " "));
        }
        return result;
    }
}


