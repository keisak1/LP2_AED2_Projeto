package com.projeto;

import edu.princeton.cs.algs4.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.lang.System.out;


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
    /**
     * The Set.
     */
    public Set<Nodes> set = new HashSet<>();

    /**
     * The Ewg.
     */
    public EdgeWeightedDigraph ewg;

    public DijkstraSP sp;

    /**
     * The Sub graph.
     */
    public EdgeWeightedDigraph subGraph;
    /**
     * The Bst size.
     */
    public Integer bstSize;

    /**
     * Gets bst size.
     *
     * @return the bst size
     */
    public Integer getBstSize() {
        return bstSize;
    }

    /**
     * Sets bst size.
     *
     * @param bstSize the bst size
     */
    public void setBstSize(Integer bstSize) {
        this.bstSize = bstSize;
    }

    /**
     * The Edges size.
     */
    public Integer edgesSize;

    /**
     * Gets edges size.
     *
     * @return the edges size
     */
    public Integer getEdgesSize() {
        return edgesSize;
    }

    /**
     * Sets edges size.
     *
     * @param edgesSize the edges size
     */
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
        ewg = new EdgeWeightedDigraph(getBstSize() + 1);
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
     *
     * @param timestamp - Date
     */
    public void overPopulatedNode(Date timestamp) {
        int count = 0;
        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            for (User user : users) {
                for (NodeVisited nodeVisited : user.getNodesVisited()) {
                    if ((node.getId().equals(nodeVisited.getNodeID()) && (nodeVisited.getDateVisited().compareTo(timestamp)) > 0)) {
                        count++;
                        if (count == 5) {
                            set.add(node);
                        }
                    }
                }
            }
            count = 0;
        }
    }

    /**
     * Checks if there is connectivity in the graph
     */
    public int checkConnectivity() {

        out.println("Graph connectivity data:");
        int flag = 0;
        Graph graph = new Graph(bstSize + 1);
        for (Ways ways : edges) {
            graph.addEdge(ways.getV(), ways.getW());
        }
        for (int i = 0; i < getBstSize() + 1; i++) {
            DepthFirstSearch dfs = new DepthFirstSearch(graph, i);
            for (int v = 0; v < graph.V(); v++) {
                if (dfs.marked(v))
                    StdOut.print(v + " ");
            }
            StdOut.println();
            if (dfs.count() != graph.V()) {
                flag = 1;
            }
        }
        if (flag == 1) {
            out.println("The graph isn't connected");
            return 1;
        } else {
            out.println("The graph is connected");
            return 0;
        }
    }

    /**
     * Checks if there is connectivity in the sub graph
     */
    public int checkSubGraphConnectivity() {
        out.println("Subgraph connectivity data:");
        Graph graph = new Graph(bstSize + 1);

        int flag = 0;
        for (Ways way : edges) {
            for (Nodes nodes : set) {
                if (nodes.getVertexID() == way.getW() || nodes.getVertexID() == way.getV()) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                graph.addEdge(way.getV(), way.getW());
            }
            flag = 0;
        }

        for (int u = 0; u < getBstSize() + 1; u++) {
            DepthFirstSearch dfs = new DepthFirstSearch(graph, u);
            for (int v = 0; v < graph.V(); v++) {
                if (dfs.marked(v))
                    StdOut.print(v + " ");
            }
            StdOut.println();
            if (dfs.count() != graph.V()) {
                flag = 1;
            }
        }
        if (flag == 1) {
            out.println("The subgraph isn't connected");
            return 1;
        } else {
            out.println("The subgraph is connected");
            return 0;
        }
    }


    /**
     * Calculates the shortest path that is non overpopulated from one vertex to another
     *
     * @param from - starting vertex
     * @param to   - to vertex
     */
    public void shortestPathNotOverpopulated(int from, int to) {
        subGraph = new EdgeWeightedDigraph(getBstSize() + 1);


        int flag = 0;
        for (Ways way : edges) {
            for (Nodes nodes : set) {
                if (nodes.getVertexID() == way.getW() || nodes.getVertexID() == way.getV()) {
                    flag = 1;
                }
            }
            if (flag == 0) {
                DirectedEdge edge = new DirectedEdge(way.getV(), way.getW(), way.getWeight());
                subGraph.addEdge(edge);
            }
            flag = 0;
        }

        sp = new DijkstraSP(subGraph, from);

    }

    /**
     * Calculates the shortest path from one vertex to another
     *
     * @param from - starting vertex
     * @param to   - to vertex
     */
    public void shortestPath(int from, int to) {
        sp = new DijkstraSP(ewg, from);
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
            for (Ways way : node.getWays()) {
                if (way == edgeToDelete) {
                    node.getWays().remove(edgeToDelete);
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
                out.println("Node removed successfully.");
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
            out.println(user.toString());
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
                    if (nodeVisited1.getDateVisited().compareTo(d) > 0) {
                        for (Integer i : bst.keys()) {
                            boolean b = Objects.equals(bst.get(i).getId(), nodeVisited1.getNodeID());
                            if (b) {
                                visitedPoI.addAll(nodeVisited1.getPoI());
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
                        for (PoI poi : bst.get(i).getPoI()) {
                            if (nodeVisited1.getDateVisited().compareTo(d) > 0 && !nodeVisited1.getPoI().contains(poi)) {
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
                            if (node.getNodeID().equals(bst.get(i).getId()) && node.getDateVisited().compareTo(d) > 0) {
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
                if (!user.getNodesVisited().contains(bst.get(i).getId()) || (user.getNodesVisited().contains(d.afterDate(d)) && user.getNodesVisited().contains(bst.get(i).getId()))) {
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
        ArrayList<User> top5usersWhoVisitedMostPoI = new ArrayList<>();
        Map<String, Integer> tempMap = new HashMap<>();
        for (User user : users) {
            int counter = 0;
            for (int i = 0; i < user.getNodesVisited().size(); i++) {
                NodeVisited node = user.getNodesVisited().get(i);
                if (node.getDateVisited().compareTo(d) > 0) {
                    counter += node.getPoI().size();
                }
            }
            tempMap.put(user.getName(), counter);
        }

        Map<String, Integer> sortedMap = sortByValue(tempMap);
        int i = 0;
        for (Map.Entry<String, Integer> map : sortedMap.entrySet()) {
            for (User user : users) {
                if (map.getKey().equals(user.getName())) {
                    top5usersWhoVisitedMostPoI.add(user);
                }
            }
            i++;
            if (i == 5) {
                break;
            }
        }

        StdOut.println(top5usersWhoVisitedMostPoI.toString());
        return top5usersWhoVisitedMostPoI;
    }

    /**
     * Sorts an unsorted map by descending order
     *
     * @param unsortMap - Map wanted to be sorted
     * @return - a sorted map
     */
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
        List<Entry<String, Integer>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }


    /**
     * Finds top 5 points of interest most visited
     *
     * @param d - the date
     * @return the array list
     */
    public ArrayList<PoI> top5PoIs(Date d) {
        ArrayList<PoI> top5PoIs = new ArrayList<>();
        Map<String, Integer> tempMap = new HashMap<>();
        int flag = 0;
        int value = 0;
        for (User user : users) {
            for (NodeVisited nodeVisited : user.getNodesVisited()) {
                if (nodeVisited.getDateVisited().compareTo(d) > 0) {
                    for (PoI poi : nodeVisited.getPoI()) {
                        for (Map.Entry<String, Integer> map : tempMap.entrySet()) {
                            if (map.getKey().equals(poi.getName())) {
                                flag = 1;
                                value = map.getValue();
                            }
                        }
                        if (flag == 1) {
                            tempMap.put(poi.getName(), value + 1);
                        } else {
                            tempMap.put(poi.getName(), value);
                        }
                    }
                }
            }
        }

        Map<String, Integer> sortedMap = sortByValue(tempMap);
        int i = 0;
        for (Map.Entry<String, Integer> map : sortedMap.entrySet()) {
            for (Integer a : bst.keys()) {
                Nodes node = bst.get(a);
                for (PoI poi : node.getPoI()) {
                    if (map.getKey().equals(poi.getName())) {
                        top5PoIs.add(poi);
                    }
                }
            }
            i++;
            if (i == 5) {
                break;
            }
        }
        return top5PoIs;
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
