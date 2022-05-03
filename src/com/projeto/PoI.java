package com.projeto;

public class PoI implements Comparable<PoI> {

  public PoI(Integer id, String name, Vehicle type){
    this.id = id;
    this.name = name;
    this.type = type;
  }
  //comentario a toa
  public PoI(){

  }

  public Vehicle type;

  public String name;

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Override
  public int compareTo(PoI o) {
    return 0;
  }
}