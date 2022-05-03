package com.projeto;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class NodeVisited {

    public NodeVisited(Long nodeID, Integer poiID, Date dateVisited, ArrayList<PoI> poI){
        this.nodeID = nodeID;
        this.poiID = poiID;
        this.dateVisited = dateVisited;
        this.poI = poI;
    }

    public NodeVisited(){

    }

    public Long nodeID;
    public Integer poiID;
    public Date dateVisited;
    public ArrayList<PoI> poI = new ArrayList<>();

    public Long getNodeID() {
        return nodeID;
    }

    public void setNodeID(Long nodeID) {
        this.nodeID = nodeID;
    }

    public Integer getPoiID() {
        return poiID;
    }

    public void setPoiID(Integer poiID) {
        this.poiID = poiID;
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
