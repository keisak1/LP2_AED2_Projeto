package com.projeto;


/**
 * The type Vehicle.
 */
public class Vehicle {
    public Vehicle(){};
    public Vehicle(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;


}