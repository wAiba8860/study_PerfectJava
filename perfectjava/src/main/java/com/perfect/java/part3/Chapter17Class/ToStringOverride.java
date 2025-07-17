package com.perfect.java.part3.Chapter17Class;

public class ToStringOverride {
    private final String field1 = "Field1";
    private final String field2 = "Field2";

    @Override
    public String toString() {
        return "field1:%s,filed2:%s".formatted(this.field1, this.field2);
    }
}
