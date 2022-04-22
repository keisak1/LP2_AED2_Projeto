package com.projeto;


import java.util.ArrayList;

public class PoI {

  public Vehicle type;

  public String name;

  public Coordenate location;

  public Integer id;


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

  public Coordenate getLocation() {
    return location;
  }

  public void setLocation(Coordenate location) {
    this.location = location;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}