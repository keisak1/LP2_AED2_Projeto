package com.projeto;

import com.projeto.algorithms.LongEdge;

import java.util.Hashtable;


public class Ways extends LongEdge {

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param v      one vertex
     * @param w      the other vertex
     * @param weight the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *                                  is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Ways(Long v, Long w, double weight) {
        super(v, w, weight);
    }

    public Ways(Long v, Long w, double weight, Long id, Hashtable<String[], String[]> osmWay, String name, String address, String postcode) {
        super(v, w, weight);
        this.osmWay = osmWay;
        this.id = id;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
    }

    public String name;

    public Long id;

    public Integer weight;

    public String address;

    public String postcode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Hashtable<String[], String[]> osmWay = new Hashtable<>();

    public Hashtable<String[], String[]> getOsmWay() {
        return osmWay;
    }

    public void setOsmWay(Hashtable<String[], String[]> osmWay) {
        this.osmWay = osmWay;
    }

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

    @Override
    public String toString() {
        return "Ways{" +
                "name=" + name + '\'' +
                ", id=" + id +
                ", weight=" + weight +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}