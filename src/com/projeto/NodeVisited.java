package com.projeto;


import java.util.ArrayList;

/**
 * The type Node visited.
 */
public class NodeVisited {

    public NodeVisited(Integer nodeID, Date dateVisited, ArrayList<PoI> poI){
        this.nodeID = nodeID;
        this.dateVisited = dateVisited;
        this.poI = poI;
    }

    public NodeVisited(){

    }

    public Integer nodeID;
    public Date dateVisited;
    public ArrayList<PoI> poI = new ArrayList<>();

    public Integer getNodeID() {
        return nodeID;
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
