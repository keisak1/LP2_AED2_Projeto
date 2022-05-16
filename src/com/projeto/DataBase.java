package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.*;

/**
 * The type Data base.
 */
public class DataBase {
    /**
     * The Bst.
     */
    public BinarySearchST<Integer, Nodes> bst = new BinarySearchST<>();
    /**
     * The Ht.
     */
    public Hashtable<String, Grafo> ht = new Hashtable<>();
    /**
     * The Users.
     */
    public ArrayList<User> users = new ArrayList<>();
    /**
     * The Edges.
     */
    public ArrayList<Ways> edges = new ArrayList<>();

    public EdgeWeightedDigraph ewg;

    public Integer bstSize;

    public Integer getBstSize() {
        return bstSize;
    }

    public void setBstSize(Integer bstSize) {
        this.bstSize = bstSize;
    }

    public Integer edgesSize;

    public Integer getEdgesSize() {
        return edgesSize;
    }

    public void setEdgesSize(Integer edgesSize) {
        this.edgesSize = edgesSize;
    }

    /**
     * Instantiates a new Data base.
     */
    public DataBase() {
    }

    /**
     * Creates graph
     */
    public void createGraph() {
        ewg = new EdgeWeightedDigraph(getBstSize() + 1, getEdgesSize() + 1);
    }

    /**
     * Prints Graph
     */
    public void printGraph() {
        StdOut.println(ewg.toString());
    }

    /**
     * Adds edges to the graph
     */
    public void addEdges() {
        for (Ways ways : edges) {
            DirectedEdge edge = new DirectedEdge(ways.getV(), ways.getW(), ways.getWeight());
            ewg.addEdge(edge);

        }
    }

    /**
     * Set of overpopulated nodes
     * @param timestamp - Date
     */
    public void overPopulatedNode(Date timestamp){
        Set<Nodes> set = new HashSet<Nodes>();
        for (User user:users) {
            for (NodeVisited nodeVisited:user.getNodesVisited()) {
                if(nodeVisited.getDateVisited()==timestamp){

                }
            }
        }
    }

    /**
     *  Calculates the shortest path from one vertex to another
     * @param from - starting vertex
     * @param to - to vertex
     */
    public void shortestPath(int from, int to) {
        DijkstraSP sp = new DijkstraSP(ewg, from);
        if (sp.hasPathTo(to)) {
            System.out.println(sp.pathTo(to));
            System.out.println("Shortest path from vertex " + from + " to vertex " + to + " is " + sp.distTo(to));
        }else{
            System.out.println("There's no such path.");
        }
    }

    /**
     * Adds the tag ( key ) and the respective Graph Object
     *
     * @param tag           - the tag
     * @param edgeOrVertice - Graph object
     */
    public void addTag(String tag, Grafo edgeOrVertice) {
        ht.put(tag, edgeOrVertice);
    }

    /**
     * Removes the tag ( key ) and the respective Graph Object
     *
     * @param tag - the tag
     */
    public void removeTag(String tag) {
        ht.remove(tag);
    }

    /**
     * Searches if tag exists in the Hashtable
     *
     * @param tag - the tag
     * @return true or false
     */
    public boolean searchTag(String tag) {
        return ht.contains(tag);
    }

    /**
     * Prints the whole Hashtable
     */
    public void printHash() {
        StdOut.println(ht.toString());
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
        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            for (Ways way : node.ways) {
                if (way == edgeToDelete) {
                    node.ways.remove(edgeToDelete);
                }
            }
        }
        edges.remove(edgeToDelete);
    }

    /**
     * Searchs for edge. If it exists or not
     *
     * @param edgeToBeSearched - the edge
     * @return the boolean
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
     */
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
    public void deleteNode(Integer key) {
        Nodes node;
        for (Integer i : bst.keys()) {
            if (i.equals(key)) {
                node = bst.get(i);
                for (User user : users) {
                    for (NodeVisited nodevisited : user.nodesVisited) {
                        if ((node.getId().equals(nodevisited.getNodeID()))) {
                            user.nodesVisited.remove(nodevisited);
                            break;
                        }
                    }
                }
                bst.delete(key);
                System.out.println("Node removed successfully.");
            }
        }
    }

    /**
     * Searches for the specified key's value
     *
     * @param key - the key
     * @return the nodes
     */
    public Nodes searchNode(int key) {
        return bst.get(key);
    }

    /**
     * Prints the whole BST
     */
    public void printBST() {
        for (Integer i : bst.keys()) {
            StdOut.println(i + " " + bst.get(i).toString());
        }
    }

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
     * @param userID - user ID
     */
    public void deleteUser(int userID) {
        users.remove(userID);
    }

    /**
     * Searches for user
     *
     * @param userID - user ID
     * @return true or false depending wether there's an user or not
     */
    public boolean searchUser(int userID) {
        return users.contains(userID);
    }

    /**
     * Prints arrayList of users
     */
    public void printUserList() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    /**
     * Finds points of interest that were visited by a user in a certain period of time
     *
     * @param d    - the date
     * @param user - user class
     * @return the array list
     */
    public ArrayList<PoI> visitedBy(Date d, User user) {
        ArrayList<PoI> visitedPoI = new ArrayList<>();
        for (User user1 : users) {
            if (user1.id.equals(user.id)) {
                for (NodeVisited nodeVisited1 : user.getNodesVisited()) {
                    if (nodeVisited1.dateVisited.beforeDate(d)) {
                        for (Integer i : bst.keys()) {
                            boolean b = Objects.equals(bst.get(i).id, nodeVisited1.nodeID);
                            if (b) {
                                visitedPoI.addAll(nodeVisited1.poI);
                            }
                        }
                    }
                }
            }
        }
        return visitedPoI;
    }

    /**
     * Finds points of interest that were not visited by a user in a certain period of time
     *
     * @param d    - the date
     * @param user - user class
     * @return the array list
     */
    public ArrayList<PoI> notVisitedBy(Date d, User user) {
        ArrayList<PoI> notVisitedPoI = new ArrayList<>();
        for (User user1 : users) {
            if (user1.id.equals(user.id)) {
                for (Integer i : bst.keys()) {
                    for (NodeVisited nodeVisited1 : user.getNodesVisited()) {
                        for (PoI poi : bst.get(i).poI) {
                            if (nodeVisited1.dateVisited.beforeDate(d) && !nodeVisited1.getPoI().contains(poi)) {
                                notVisitedPoI.add(poi);
                            }
                        }
                    }
                }
            }
        }
        return notVisitedPoI;
    }

    /**
     * Finds points of interest that were visited in a certain period of time
     *
     * @param d - the date
     * @param p - point of interest class
     * @return the array list
     */
    public ArrayList<User> whoVisited(Date d, PoI p) {
        ArrayList<User> usersWhoVisited = new ArrayList<>();
        for (User user : users) {
            for (NodeVisited node : user.getNodesVisited()) {
                for (PoI poi : node.getPoI()) {
                    if (poi == p) {
                        for (Integer i : bst.keys()) {
                            if (node.nodeID.equals(bst.get(i).id) && node.dateVisited.beforeDate(d)) {
                                usersWhoVisited.add(user);
                            }
                        }
                    }
                }
            }
        }
        return usersWhoVisited;
    }

    /**
     * Finds points of interest that weren't visited on a certain period of time
     *
     * @param d - the date
     * @return the array list
     */
    public ArrayList<PoI> notVisitedPoI(Date d) {
        ArrayList<PoI> notVisited = new ArrayList<>();
        for (User user : users) {
            for (Integer i : bst.keys()) {
                if (!user.getNodesVisited().contains(bst.get(i).id) || (user.getNodesVisited().contains(d.afterDate(d)) && user.getNodesVisited().contains(bst.get(i).id))) {
                    notVisited.addAll(bst.get(i).getPoI());
                }
            }
        }
        return notVisited;
    }

    /**
     * Finds top 5 users that visited most points of interest
     *
     * @param d - the date
     * @return the array list
     */
    public ArrayList<User> top5Users(Date d) {
        ArrayList<User> top5usersWhoVisitedMostPoI = new ArrayList<>(5);
        for (User user : users) {
            int counter = user.getNodesVisited().size();
            for (NodeVisited node : user.getNodesVisited()) {
                if (node.dateVisited.beforeDate(d)) {
                    if (top5usersWhoVisitedMostPoI.isEmpty()) {
                        top5usersWhoVisitedMostPoI.add(user);
                    }
                    for (int i = 0; i < 5; i++) {
                        if (counter > top5usersWhoVisitedMostPoI.get(i).nodesVisited.size()) {
                            top5usersWhoVisitedMostPoI.add(i, user);
                            break;
                        } else if (top5usersWhoVisitedMostPoI.get(i) == null) {
                            top5usersWhoVisitedMostPoI.add(user);
                        }
                    }
                }
            }
        }
        return top5usersWhoVisitedMostPoI;
    }

    /**
     * Finds top 5 points of interest most visited
     *
     * @param d - the date
     * @return the array list
     */
    public ArrayList<PoI> top5PoIs(Date d) {
        ArrayList<PoI> top5PoIsWhoWereVisited = new ArrayList<>(5);
        BinarySearchST<PoI, Integer> funcBst = new BinarySearchST<>();
        for (Integer i : bst.keys()) {
            for (User user : users) {
                for (NodeVisited node : user.getNodesVisited()) {
                    if (node.dateVisited.beforeDate(d)) {
                        for (PoI poi : bst.get(i).getPoI()) {
                            for (PoI poi1 : node.getPoI()) {
                                if (poi == poi1) {
                                    if (funcBst.contains(poi)) {
                                        int aux = funcBst.get(poi);
                                        funcBst.put(poi, aux + 1);
                                    } else {
                                        funcBst.put(poi, 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            Integer max = 0;
            for (PoI poi : funcBst.keys()) {
                if (max < funcBst.get(poi)) {
                    max = funcBst.get(poi);
                }
            }
            for (PoI pois : funcBst.keys()) {
                if (Objects.equals(max, funcBst.get(pois))) {
                    top5PoIsWhoWereVisited.add(pois);
                    funcBst.delete(pois);
                }
            }
        }
        return top5PoIsWhoWereVisited;
    }

    /**
     * Looks for a tag in the Hashtable, if it is found, returns an ArrayList of Graph Object
     *
     * @param tag - the tag
     * @return tagFound array list
     */
    public ArrayList<Grafo> tagFound(String tag) {
        ArrayList<Grafo> tagFound = new ArrayList<>();
        if (searchTag(tag)) {
            tagFound.add(ht.get(tag));
        }
        return tagFound;
    }
}
