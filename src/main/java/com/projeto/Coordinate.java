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

    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}