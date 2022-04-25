package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class DataBase {
    public BinarySearchST<Integer, Nodes> bst = new BinarySearchST<>();
    public ArrayList<NodeVisited> nodeVisited = new ArrayList<>();
    public Hashtable<Integer, Ways> ht = new Hashtable<>();
    public ArrayList<User> users = new ArrayList<>();
    ArrayList<Nodes> vertices = new ArrayList<>();
    ArrayList<Ways> edges = new ArrayList<>();

    public DataBase() {
    }


    /**
     * Inserts the specified vertice into the vertice ArrayList
     *
     * @param newVertice - node class
     */
    public void addVertice(Nodes newVertice) {
        vertices.add(newVertice);
    }

    /**
     * Deletes vertice's node
     *
     * @param verticeToDelete - the vertice
     */
    public void deleteVertice(Nodes verticeToDelete) {
        vertices.remove(verticeToDelete);
    }


    /**
     * Searchs for vertice. If it exists or not
     *
     * @param verticeToBeSearched - vertice to be searched
     */
    public boolean searchVertice(Nodes verticeToBeSearched) {
        return vertices.contains(verticeToBeSearched);
    }

    /**
     * Prints the whole vertice ArrayList
     */
    public void printVertices() {
        for (Nodes vertice : vertices) {
            StdOut.print(vertice + "\n");
        }
    }

    /**
     * Inserts the specified edge into the ways ArrayList
     *
     * @param newEdge - node class
     */
    public void addEdge(Ways newEdge) {
        edges.add(newEdge);
    }

    /**
     * Deletes edge's node
     *
     * @param edgeToDelete - the edge
     */
    public void deleteEdge(Ways edgeToDelete) {
        edges.remove(edgeToDelete);
    }

    /**
     * Searchs for edge. If it exists or not
     *
     * @param edgeToBeSearched
     */
    public boolean searchEdge(Ways edgeToBeSearched) {
        return edges.contains(edgeToBeSearched);
    }

    /**
     * Prints the whole edge ArrayList
     */
    public void printEdges() {
        for (Ways edge : edges) {
            StdOut.print(edge + "\n");
        }
    }

    /**
     * Inserts the specified key-value into the symbol table
     *
     * @param key  - the key
     * @param node - node class
     */
    public void addNode(int key, Nodes node) {
        bst.put(key, node);
    }

    /**
     * Edits BST through key
     *
     * @param key  - the key
     * @param node - node class
     **/
    public void editNode(int key, Nodes node) {
        if (bst.get(key) != null) {
            bst.put(key, node);
        }
    }

    /**
     * Deletes key's node
     *
     * @param key - the key
     */
    public void deleteNode(int key) {
        bst.delete(key);
    }

    /**
     * Searches for the specified key's value
     *
     * @param key - the key
     */
    public Nodes searchNode(int key) {
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
    /*public void addWay(int key, Ways way) {
        ht.put(key, way);
    }*/

    /**
     * Edits HT through key
     * @param key - the key
     * @param way - way class
     */
    /*public void editWay(int key, Ways way) {
        if (ht.get(key) != null) {
            ht.put(key, way);
        }
    }*/

    /**
     * Deletes key's way
     * @param key - the key
     */
    /*public void deleteWay(int key){
        ht.remove(key);
    }*/

    /**
     * Searches for the specified key's value
     * @param key - the key
     */
    /*public Ways searchWay(int key){
        return ht.get(key);
    }*/

    /**
     * Prints the whole HT
     */
    /*public void printHT(){
        Enumeration<Integer> keys = ht.keys();
        while(keys.hasMoreElements()){
            System.out.println(keys.nextElement());
        }
    }*/

    /**
     * Inserts user into Arraylist
     *
     * @param user - user object
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Finds user name and replaces with the new object
     *
     * @param user - user object
     */
    public void editUser(User user) {
        Iterator<User> u = users.iterator();
        int i = 0;
        while (u.hasNext()) {
            if (u.next().name.equals(user.name)) {
                users.set(i, user);
            }
            i++;
        }
    }

    /**
     * Deletes given user
     *
     * @param user - user object
     */
    public void deleteUser(User user) {
        users.remove(user);
    }

    /**
     * Searches for user
     *
     * @param user - user object
     * @return true or false depending wether there's an user or not
     */
    public boolean searchUser(User user) {
        return users.contains(user);
    }

    /**
     * Prints arrayList of users
     */
    public void printUserList() {
        for (User user : users) {
            System.out.println(user);
        }
    }


    public ArrayList<PoI> visitedBy(Date d, User user) {
        ArrayList<PoI> visitedPoI = new ArrayList<>();
        for (User user1 : users) {
            if (user1.id.equals(user.id)) {
                for (NodeVisited nodeVisited1:user.getNodesVisited()) {
                    if(nodeVisited1.dateVisited.beforeDate(d)){
                        for (Integer i : bst.keys()) {
                            boolean b = bst.get(i).id == nodeVisited1.nodeID;
                            if(b){
                                visitedPoI.add(bst.get(i).poI);
                            }
                        }
                    }
                }
            }
        }
        return visitedPoI;
    }

    public ArrayList<PoI> notVisitedBy(Date d, User user) {
        return null;
    }

    public ArrayList whoVisited(Date d, PoI p) {
        return null;
    }



    public ArrayList topUsers(Date d, ArrayList userList) {
        return null;
    }

    public ArrayList topPoI(Date d, ArrayList poiList) {
        return null;
    }


    public ArrayList notVisitedPoI(Date d) {
        return null;
    }
}
