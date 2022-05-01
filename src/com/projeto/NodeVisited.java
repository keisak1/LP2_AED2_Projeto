package com.projeto;

import java.util.ArrayList;

public class NodeVisited {

    public Long nodeID;
    public Integer userID;
    public Date dateVisited;
    public ArrayList<PoI> poI;

    public Long getNodeID() {
        return nodeID;
    }

    public void setNodeID(Long nodeID) {
        this.nodeID = nodeID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public void setPoI(ArrayList<PoI> poI) {
        this.poI = poI;
    }
}
