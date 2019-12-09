package com.hashmaker.data;

public class Hash {
    private String type;
    private String value;
    private double time;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Hash{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", time=" + time +
                '}';
    }
}
