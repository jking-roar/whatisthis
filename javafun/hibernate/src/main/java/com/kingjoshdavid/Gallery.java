package com.kingjoshdavid;

import java.util.Set;

public class Gallery {
    private int identifier;
    private Set<Peanut> members;
    private String curtain;
    private Set<Peanut> vips;

    public Set<Peanut> getVips() {
        return vips;
    }

    public void setVips(Set<Peanut> vips) {
        this.vips = vips;
    }

    public String getCurtain() {
        return curtain;
    }

    public void setCurtain(String curtain) {
        this.curtain = curtain;
    }

    public Set<Peanut> getMembers() {
        return members;
    }

    public void setMembers(Set<Peanut> members) {
        this.members = members;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "identifier=" + identifier +
                ", members=" + members +
                ", vips=" + vips +
                ", curtain='" + curtain + '\'' +
                '}';
    }
}
