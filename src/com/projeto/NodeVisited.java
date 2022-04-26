package com.projeto;

public class NodeVisited {

    public Integer nodeID;
    public Integer userID;
    public Date dateVisited;
    public Coordenate point;
    public PoI poI;

    public Integer getNodeID() {
        return nodeID;
    }

    public void setNodeID(Integer nodeID) {
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

    public Coordenate getPoint() {
        return point;
    }

    public void setPoint(Coordenate point) {
        this.point = point;
    }

    public PoI getPoI() {
        return poI;
    }

    public void setPoI(PoI poI) {
        this.poI = poI;
    }
}
