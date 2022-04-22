package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

public class DataBase {


    public BST<Integer, Nodes> addNode(int key, Nodes node) {

        BST<Integer, Nodes> st = new BST<Integer, Nodes>();
        st.put(key, node);
        return st;
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
