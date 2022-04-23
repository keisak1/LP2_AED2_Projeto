package com.projeto;


import java.util.ArrayList;
import java.util.List;


public class Ways {

  public String name;

  public Integer weight;

  public ArrayList<Nodes> nodes;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public List<Nodes> getNodes() {
    return nodes;
  }

  public void setNodes(ArrayList<Nodes> nodes) {
    this.nodes = nodes;
  }
}