package com.projeto;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class Ways {

    public Hashtable<String, String> osmWay = new Hashtable<>();

    public String name;

    public Integer weight;

    public Nodes nodestart;

    public Nodes nodend;

    public Hashtable<String, String> getOsmWay() {
        return osmWay;
    }

    public void setOsmWay(Hashtable<String, String> osmWay) {
        this.osmWay = osmWay;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Nodes getNodestart() {
        return nodestart;
    }

    public void setNodestart(Nodes nodestart) {
        this.nodestart = nodestart;
    }

    public Nodes getNodend() {
        return nodend;
    }

    public void setNodend(Nodes nodend) {
        this.nodend = nodend;
    }
}