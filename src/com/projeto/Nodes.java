package com.projeto;


import java.util.List;

public class Nodes {

    public Integer id;

    public Coordenate point;

    public List<Ways> ways;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coordenate getPoint() {
        return point;
    }

    public void setPoint(Coordenate point) {
        this.point = point;
    }

    public List<Ways> getWays() {
        return ways;
    }

    public void setWays(List<Ways> ways) {
        this.ways = ways;
    }
}

