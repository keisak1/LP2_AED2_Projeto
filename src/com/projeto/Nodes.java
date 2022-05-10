package com.projeto;


import java.util.ArrayList;
import java.util.Hashtable;

/**
 * The type Nodes.
 */
public class Nodes {


    public Hashtable<String[], String[]> osmNode = new Hashtable<>();

    public Nodes(Hashtable<String[], String[]> osmNode, Long id, Coordinate point, ArrayList<PoI> poI, ArrayList<Ways> ways) {
        this.osmNode = osmNode;
        this.id = id;
        this.point = point;
        this.poI = poI;
        this.ways = ways;
    }

    public Nodes() {

    }

    public Hashtable<String[], String[]> getOsmNode() {
        return osmNode;
    }

    public void setOsmNode(Hashtable<String[], String[]> osmNode) {
        this.osmNode = osmNode;
    }

    public Long id;

    public Coordinate point;

    public ArrayList<PoI> poI = new ArrayList<>();

    public ArrayList<Ways> ways = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWays(ArrayList<Ways> ways) {
        this.ways = ways;
    }

    public ArrayList<PoI> getPoI() {
        return poI;
    }

    public void setPoI(PoI poI) {
        this.poI.add(poI);
    }

    public Coordinate getPoint() {
        return point;
    }

    public void setPoint(Coordinate point) {
        this.point = point;
    }

    public ArrayList<Ways> getWays() {
        return ways;
    }

    public void setWays(Ways ways) {
        this.ways.add(ways);
    }

    @Override
    public String toString() {
        return "Nodes{" +
                "id=" + id +
                ", point=" + "(" + point.x + "," + point.y+ ")" +
                ", poI=" + poI +
                ", ways=" + ways +
                '}';
    }
}

