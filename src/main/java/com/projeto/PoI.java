package com.projeto;

/**
 * The type Po i.
 */
public class PoI extends Nodes {

    public PoI(Integer id, String name, Vehicle type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public PoI() {

    }

    private Vehicle type;

    private String name;

    private Integer id;

    public Vehicle getType() {
        return type;
    }

    public void setType(Vehicle type) {
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

    public void setId(Integer id) {
        this.id = id;
    }

}