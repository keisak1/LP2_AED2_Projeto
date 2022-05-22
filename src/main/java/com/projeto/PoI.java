package com.projeto;

/**
 * The type Po i.
 */
public class PoI extends Nodes {

    public PoI(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public PoI() {

    }

    private String type;

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

    public void setId(Integer id) {
        this.id = id;
    }

}