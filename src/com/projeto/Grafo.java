package com.projeto;

public class Grafo extends PoI {

  public Ways edges;

  public Nodes vertices;

  public Ways getEdges() {
    return edges;
  }

  public void setEdges(Ways edges) {
    this.edges = edges;
  }

  public Nodes getVertices() {
    return vertices;
  }

  public void setVertices(Nodes vertices) {
    this.vertices = vertices;
  }
}