package com.projeto;


import java.util.ArrayList;

/**
 * The type User.
 */
public class User {

    public User(Integer id, String name, Date birthday, String vehicle, ArrayList<NodeVisited> nodesVisited){
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.vehicle = vehicle;
        this.nodesVisited = nodesVisited;
    }

    public User(){

    }

    public String name;

    public Integer id;

    public Date birthday;

    public String vehicle;

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

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", birthday=" + birthday +
                ", vehicle=" + vehicle +
                ", nodesVisited=" + nodesVisited +
                '}';
    }


}