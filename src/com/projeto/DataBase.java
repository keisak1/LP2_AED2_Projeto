package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class DataBase {
    public BinarySearchST<Integer, Nodes> bst = new BinarySearchST<>();
    public Hashtable<Integer, Ways> ht = new Hashtable<>();

    public DataBase() {
    }

    /**
     *  Inserts the specified key-value into the symbol table
     * @param key - the key
     * @param node - node class
     */
    public void addNode(int key, Nodes node) {
        bst.put(key, node);
    }

    /**
     * Edits BST through key
     * @param key - the key
     * @param node - node class
     **/
    public void editNode(int key, Nodes node) {
        if (bst.get(key) != null) {
            bst.put(key, node);
        }
    }

    /**
     * Deletes key's node
     * @param key - the key
     */
    public void deleteNode(int key){
        bst.delete(key);
    }

    /**
     * Searches for the specified key's value
     * @param key - the key
     */
    public Nodes searchNode(int key){
        return bst.get(key);
    }

    /**
     * Prints the whole BST
     */
    public void printBST() {
        for (Integer i : bst.keys()) {
            StdOut.println(i + " " + bst.get(i));
        }
    }

    /**
     *  Inserts the specified key-value into the symbol table
     * @param key - the key
     * @param way - way class
     */
    public void addWay(int key, Ways way) {
        ht.put(key, way);
    }

    /**
     * Edits HT through key
     * @param key - the key
     * @param way - way class
     **/
    public void editWay(int key, Ways way) {
        if (ht.get(key) != null) {
            ht.put(key, way);
        }
    }

    /**
     * Deletes key's way
     * @param key - the key
     */
    public void deleteWay(int key){
        ht.remove(key);
    }

    /**
     * Searches for the specified key's value
     * @param key - the key
     */
    public Ways searchWay(int key){
        return ht.get(key);
    }

    /**
     * Prints the whole HT
     */
    public void printHT(){
        Enumeration<Integer> keys = ht.keys();
        while(keys.hasMoreElements()){
            System.out.println(keys.nextElement());
        }
    }

    public ArrayList whoVisited(Date d, PoI p) {
        return null;
    }

    public ArrayList whoNotVisited(Date d, PoI p) {
        return null;
    }

    public ArrayList topUsers(Date d, ArrayList userList) {
        return null;
    }

    public ArrayList topPoI(Date d, ArrayList poiList) {
        return null;
    }

    public ArrayList visitedPoI(Date d) {
        return null;
    }

    public ArrayList notVisitedPoI(Date d) {
        return null;
    }
}
