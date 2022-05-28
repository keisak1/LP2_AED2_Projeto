package com.projeto;

import java.io.Serializable;

/**
 * The type Po i.
 */
public class PoI extends Nodes implements Serializable {

    public PoI(Integer id, Integer nodeID, String name, String type) {
        this.id = id;
        this.nodeID = nodeID;
        this.name = name;
        this.type = type;
    }

    public PoI() {

    }

    private String type;

    private Integer nodeID;

    private String name;

    private Integer id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNodeID() {
        return nodeID;
    }

    public void setNodeID(Integer nodeID) {
        this.nodeID = nodeID;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return name + '\'' + ", id=" + id;
    }
}