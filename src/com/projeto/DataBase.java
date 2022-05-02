package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.*;

public class DataBase {
    public BinarySearchST<Long, Nodes> bst = new BinarySearchST<>();
    public Hashtable<String, Grafo> ht = new Hashtable<>();
    public ArrayList<User> users = new ArrayList<>();
    public ArrayList<Ways> edges = new ArrayList<>();

    public DataBase() {
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
        for (Long i : bst.keys()) {
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
    public void addNode(long key, Nodes node) {
        bst.put(key, node);
    }

    /**
     * Edits BST through key
     *
     * @param key  - the key
     * @param node - node class
     **/
    public void editNode(long key, Nodes node) {
        if (bst.get(key) != null) {
            bst.put(key, node);
        }
    }

    /**
     * Deletes key's node
     *
     * @param key - the key
     */
    public void deleteNode(long key) {
        Nodes node = new Nodes();
        for (Long i : bst.keys()) {
            if (i == key) {
                node = bst.get(i);
            }
        }
        for (User user : users) {
            for (NodeVisited nodevisited : user.nodesVisited) {
                if (Objects.equals(node.getId(), nodevisited.getNodeID())) {
                    user.nodesVisited.remove(nodevisited);
                }
            }
        }
        bst.delete(key);
    }

    /**
     * Searches for the specified key's value
     *
     * @param key - the key
     */
    public Nodes searchNode(long key) {
        return bst.get(key);
    }

    /**
     * Prints the whole BST
     */
    public void printBST() {
        for (Long i : bst.keys()) {
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
                for (NodeVisited nodeVisited1 : user.getNodesVisited()) {
                    if (nodeVisited1.dateVisited.beforeDate(d)) {
                        for (Long i : bst.keys()) {
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

    public ArrayList<PoI> notVisitedBy(Date d, User user) {
        ArrayList<PoI> notVisitedPoI = new ArrayList<>();

        for (User user1 : users) {
            if (user1.id.equals(user.id)) {
                for (NodeVisited nodeVisited1 : user.getNodesVisited()) {
                    if (nodeVisited1.dateVisited.beforeDate(d)) {
                        for (Long i : bst.keys()) {
                            boolean b = bst.get(i).id.equals(nodeVisited1.nodeID);
                            if (!b) {
                                notVisitedPoI.addAll(nodeVisited1.getPoI());
                            }
                        }
                    }
                }
            }
        }
        return notVisitedPoI;
    }

    public ArrayList<User> whoVisited(Date d, PoI p) {
        ArrayList<User> usersWhoVisited = new ArrayList<>();
        for (User user : users) {
            for (NodeVisited node : user.getNodesVisited()) {
                for (PoI poi : node.getPoI()) {
                    if (poi == p) {
                        for (Long i : bst.keys()) {
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

    public ArrayList<PoI> notVisitedPoI(Date d) {
        ArrayList<PoI> notVisited = new ArrayList<>();
        for (User user : users) {
            for (Long i : bst.keys()) {
                if (!user.getNodesVisited().contains(bst.get(i).id) || (user.getNodesVisited().contains(d.afterDate(d)) && user.getNodesVisited().contains(bst.get(i).id))) {
                    notVisited.addAll(bst.get(i).getPoI());
                }
            }
        }
        return notVisited;
    }

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

    public ArrayList<PoI> top5PoIs(Date d) {
        ArrayList<PoI> top5PoIsWhoWereVisited = new ArrayList<>(5);
        BinarySearchST<PoI, Integer> funcBst = new BinarySearchST<>();
        for (Long i : bst.keys()) {
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
     * @return tagFound
     */
    public ArrayList<Grafo> tagFound(String tag) {
        ArrayList<Grafo> tagFound = new ArrayList<>();
        if (searchTag(tag)) {
            tagFound.add(ht.get(tag));
        }
        return tagFound;
    }
}
