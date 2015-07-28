package com.kingjoshdavid;

public class Peanut {
    private Integer roast;
    private Integer identifier;

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Integer getRoast() {
        return roast;
    }

    public void setRoast(Integer roast) {
        this.roast = roast;
    }

    @Override
    public String toString() {
        return "Peanut{" +
                "roast=" + roast +
                ", identifier=" + identifier +
                '}';
    }
}
