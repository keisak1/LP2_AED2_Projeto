package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class DataBase {
    public BinarySearchST<Integer, Nodes> st = new BinarySearchST<Integer, Nodes>();

    public DataBase() {
    }

    /**
     *  Inserts the specified key-value into the symbol table
     * @param key - the key
     * @param node - node class
     */
    public void addNode(int key, Nodes node) {
        st.put(key, node);
    }

    /**
     * Edits BST through key
     * @param key - the key
     * @param node - node class
     **/
    public void editNode(int key, Nodes node) {
        if (st.get(key) != null) {
            st.put(key, node);
        }
    }

    /**
     * Deletes key's node
     * @param key - the key
     */
    public void deleteNode(int key){
        st.delete(key);
    }

    /**
     * Searches for the specified key's value
     * @param key - the key
     */
    public Nodes searchNode(int key){
        return st.get(key);
    }

    public void printBST(){
        s
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
