package com.projeto;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Nodes {
    public Hashtable<String, String> osmNode = new Hashtable<>();

    public Integer id;

    public Coordenate point;

    public String address;

    public String postcode;

    public ArrayList<PoI> poI;

    public ArrayList<Ways> ways;

    public Integer getId() {
        return id;
    }

    public ArrayList<PoI> getPoI() {
        return poI;
    }

    public void setPoI(ArrayList<PoI> poI) {
        this.poI = poI;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coordenate getPoint() {
        return point;
    }

    public void setPoint(Coordenate point) {
        this.point = point;
    }

    public List<Ways> getWays() {
        return ways;
    }

    public void setWays(ArrayList<Ways> ways) {
        this.ways = ways;
    }

    public Hashtable<String, String> getOsmNode() {
        return osmNode;
    }

    public void setOsmNode(Hashtable<String, String> osmNode) {
        this.osmNode = osmNode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}

