package com.epam.rd.autocode.hashtableopen816;

public class ListNode {
    private Integer key;
    private Object value;
    private boolean flag;

    public void markedAsRemoved() {
        flag = true;
    }

    public boolean notMarkedAsRemoved() {
        return !flag;
    }

    public Integer getKey() {
        if (key != null) return key;
        return 0;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
