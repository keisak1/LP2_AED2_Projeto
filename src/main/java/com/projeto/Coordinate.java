package com.projeto;

/**
 * The type Coordinate.
 */
public class Coordinate {

  public Coordinate(double x, double y) {
    this.x = x;
    this.y = y;
  }

  private double x;

  private double y;

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return x + "," + y;
  }
}