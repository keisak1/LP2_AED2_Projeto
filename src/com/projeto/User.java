package com.projeto;


import java.util.ArrayList;

public class User {

    public String name;

    public Integer id;

    public Date birthday;

    public Vehicle vehicle;

    public ArrayList<NodeVisited> nodesVisited;

    public ArrayList<NodeVisited> getNodesVisited() {
        return nodesVisited;
    }

    public void setNodesVisited(ArrayList<NodeVisited> nodesVisited) {
        this.nodesVisited = nodesVisited;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}