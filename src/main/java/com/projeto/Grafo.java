package com.projeto;


import java.util.ArrayList;

public class Grafo {

    public Grafo(ArrayList<Ways> edges, ArrayList<Nodes> vertices) {
        this.edges = edges;
        this.vertices = vertices;
    }

    private final ArrayList<Ways> edges;

    private final ArrayList<Nodes> vertices;

    public ArrayList<Ways> getEdges() {return edges;}

    public void setEdges(Ways edges) {
        this.edges.add(edges);
    }

    public ArrayList<Nodes> getVertices() {
        return vertices;
    }

    public void setVertices(Nodes vertices) {
        this.vertices.add(vertices);
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}' + "\n";
    }
}