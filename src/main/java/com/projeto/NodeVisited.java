package com.projeto;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Node visited.
 */
public class NodeVisited extends User implements Serializable {

    public NodeVisited(Integer nodeID, Date dateVisited, ArrayList<PoI> poI) {
        this.nodeID = nodeID;
        this.dateVisited = dateVisited;
        this.poI = poI;

    }

    public NodeVisited() {

    }

    private Integer userID;
    private Integer nodeID;
    private Date dateVisited;
    private ArrayList<PoI> poI = new ArrayList<>();

    public Integer getNodeID() {
        return nodeID;
    }

    public Integer getUserID() {
        return this.getId();
    }

    public void setNodeID(Integer nodeID) {
        this.nodeID = nodeID;
    }

    public Date getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(Date dateVisited) {
        this.dateVisited = dateVisited;
    }

    public ArrayList<PoI> getPoI() {
        return poI;
    }

    public void setPoI(PoI poi) {
        this.poI.add(poi);
    }
}
