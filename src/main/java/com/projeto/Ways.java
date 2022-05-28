package com.projeto;

import com.projeto.algorithms.DirectEdgeSerial;

import java.io.Serializable;
import java.util.Hashtable;


/**
 * The type Ways.
 */
public class Ways extends DirectEdgeSerial implements Serializable {

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


    public Ways(int v, int w, double weight, Integer id, Hashtable<String, String> osmWay, String name, String address, String postcode) {
        super(v, w, weight);
        this.osmWay = osmWay;
        this.id = id;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
    }

    private String name;

    private Integer id;

    private Double weight;

    private String address;

    private String postcode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Hashtable<String, String> osmWay = new Hashtable<>();

    public Hashtable<String, String> getOsmWay() {
        return osmWay;
    }

    public void setOsmWay(Hashtable<String, String> osmWay) {
        this.osmWay = osmWay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getV() {
        return super.from();
    }

    public int getW() {
        return super.to();
    }

    public double getWeight() {
        return super.weight();
    }

    @Override
    public String toString() {
        return "Ways{" +
                "name=" + name + '\'' +
                ", id=" + id +
                ", weight=" + getWeight() +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                '}';
    }
}