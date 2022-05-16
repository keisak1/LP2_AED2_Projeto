package com.projeto;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;

public class Main {

    static DataBase dataBase = new DataBase();

    public static void main(String[] args) {
        loadFromFiles();
        now();
    }

    /**
     * Prints all database on the instant of the calling method
     */
    private static void now() {
        Date d = new Date(29, 4, 2022);
        dataBase.printBST();
        dataBase.printUserList();
        dataBase.printEdges();
        dataBase.printHash();
        dataBase.tagFound("crossing");
        //dataBase.top5PoIs(d);
        //dataBase.top5Users(d);
        dataBase.notVisitedPoI(d);
        dataBase.createGraph();
        dataBase.addEdges();
        dataBase.printGraph();
        dataBase.overPopulatedNode(d);
        dataBase.shortestPath(19,22);
        dataBase.shortestPathNotOverpopulated(19,22);
        dataBase.checkConnectivity();
        dataBase.checkSubGraphConnectivity();
    }

    /**
     * Loads content from files to the database
     */
    private static void loadFromFiles() {
        String fileSource1 = "data\\dataset1_nodes.txt";
        String fileSource2 = "data\\dataset1_ways_nodepairs.txt";
        String fileSource3 = "data\\dataset1_pois.txt";
        String fileSource4 = "data\\dataset1_users.txt";
        loadFromFileNodes(fileSource1);
        loadFromFileWays(fileSource2);
        loadFromFilePoI(fileSource3);
        loadFromFileUser(fileSource4);
        fillHash();
    }

    /**
     * Loads content from file path to Nodes database
     *
     * @param path - String
     */
    private static void loadFromFileNodes(String path) {
        int size = 0;
        In in = new In(path);
        in.readLine();
        int id;
        int vertexID;
        String[] value;
        String[] tag;
        float coordenateX;
        float coordenateY;
        while (!in.isEmpty()) {
            String[] text = in.readLine().split(",");
            id = Integer.parseInt(text[0]);
            coordenateX = Float.parseFloat(text[1]);
            coordenateY = Float.parseFloat(text[2]);
            vertexID = Integer.parseInt(text[3]);
            tag = new String[10];
            value = new String[10];
            int tagNumb = text.length - 4;
            int i = 0, u = 4;
            while (tagNumb != 0) {
                tag[i] = text[u];
                value[i] = text[u + 1];
                tagNumb -= 2;
                i++;
                u += 2;
            }
            Hashtable<String[], String[]> osm = new Hashtable<>();
            osm.put(tag, value);
            Coordinate coordinate = new Coordinate(coordenateX, coordenateY);
            ArrayList<Ways> waysArrayList = new ArrayList<>();
            ArrayList<PoI> PoIArrayList = new ArrayList<>();
            Nodes node = new Nodes(osm, id, vertexID, coordinate, PoIArrayList, waysArrayList);
            dataBase.bst.put(id, node);
            dataBase.setBstSize(size++);
        }
    }

    /**
     * Loads content from file path to Ways database
     *
     * @param path - String
     */
    private static void loadFromFileWays(String path) {
        int size = 0;
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String address = null, address2 = null, address3 = null;
            String[] text = in.readLine().split(",");
            Integer id = Integer.parseInt(text[0]);
            int nodeID1 = Integer.parseInt(text[1]);
            int nodeID2 = Integer.parseInt(text[2]);
            float weight = Float.parseFloat(text[3]);
            String[] value = new String[10];
            String[] tag = new String[10];
            int tagNumb = text.length - 4;
            int i = 0, u = 4;
            while (tagNumb != 0) {
                if (text[u].equals("name")) {
                    String name = text[u + 1];
                    u += 2;
                    tagNumb -= 2;
                }
                switch (text[u]) {
                    case "addr:city" -> {
                        address = text[u + 1];
                        u += 2;
                        tagNumb -= 2;
                        continue;
                    }
                    case "addr:street" -> {
                        address2 = text[u + 1];
                        u += 2;
                        tagNumb -= 2;
                        continue;
                    }
                    case "addr:postcode" -> {
                        address3 = text[u + 1];
                        u += 2;
                        tagNumb -= 2;
                        continue;
                    }
                }
                tag[i] = text[u];
                value[i] = text[u + 1];
                tagNumb -= 2;
                i++;
                u += 2;
            }
            Hashtable<String[], String[]> osmWay = new Hashtable<>();
            osmWay.put(tag, value);
            Nodes node = dataBase.bst.get(nodeID1);
            Nodes node2 = dataBase.bst.get(nodeID2);
            Ways way = new Ways(node.getVertexID(), node2.getVertexID(), weight, id, osmWay, address2, address, address3);
            dataBase.edges.add(way);
            node2.setWays(way);
            node.setWays(way);
            dataBase.setEdgesSize(size++);
        }
    }

    /**
     * Loads content from file path to PoI database
     *
     * @param path - String
     */
    private static void loadFromFilePoI(String path) {
        In in = new In(path);
        in.readLine();
        int poiID;
        int nodeID;
        String name, Vehicle;
        Vehicle vehicle = new Vehicle();
        while (!in.isEmpty()) {
            String[] text = in.readLine().split(",");
            poiID = Integer.parseInt(text[0]);
            nodeID = Integer.parseInt(text[1]);
            name = text[2];
            if (!Objects.equals(text[3], "none")) {
                Vehicle = text[3];
            } else {
                Vehicle = null;
            }
            PoI poi = new PoI(poiID, name, vehicle);
            Nodes node = dataBase.bst.get(nodeID);
            node.poI.add(poi);
        }
    }

    /**
     * Loads content from file path to User database
     *
     * @param path - String
     */
    private static void loadFromFileUser(String path) {
        In in = new In(path);
        in.readLine();
        int nodeID;
        int tagNumb, u, poiID, userID;
        String name, vehicle;
        String[] bd, nodesVisited, dateNodeVisited;
        ArrayList<PoI> poI = new ArrayList<>();
        ArrayList<NodeVisited> visitedNodes = new ArrayList<>();
        while (!in.isEmpty()) {
            String[] text = in.readLine().split(",");
            userID = Integer.parseInt(text[0]);
            name = text[1];
            bd = text[2].split("-");
            vehicle = text[3];
            tagNumb = text.length - 4;
            u = 4;
            while (tagNumb >= 0) {
                nodesVisited = text[u].split(":");
                nodeID = Integer.parseInt(nodesVisited[1]);
                poiID = Integer.parseInt(nodesVisited[2]);
                dateNodeVisited = nodesVisited[3].split("-");
                Date nodeVisitedDate = new Date(Integer.parseInt(dateNodeVisited[0]), Integer.parseInt(dateNodeVisited[1]), Integer.parseInt(dateNodeVisited[2]));
                Nodes node = dataBase.bst.get(nodeID);
                for (PoI poi1 : node.getPoI()) {
                    if (poi1 == node.poI.get(poiID)) {
                        poI.add(poi1);
                    }
                }
                NodeVisited nodeVisited = new NodeVisited(nodeID, nodeVisitedDate, poI);
                visitedNodes.add(nodeVisited);
                u += 2;
                tagNumb -= 2;
            }
            Vehicle vehicle1 = new Vehicle();
            Date bday = new Date(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));
            User user = new User(userID, name, bday, vehicle1, visitedNodes);
            dataBase.addUser(user);
        }
    }

    /**
     * Fills HashTable from database with Nodes and Ways tags
     */
    private static void fillHash() {
        for (Integer i : dataBase.bst.keys()) {
            Nodes node = dataBase.bst.get(i);
            Set<String[]> a = node.osmNode.keySet();
            String[] text = new String[0];
            for (String[] key : a) text = key;
            int x = 0;
            ArrayList<Nodes> vertice = new ArrayList<>();
            ArrayList<Ways> edges = new ArrayList<>();
            Grafo grafo = new Grafo(edges, vertice);
            grafo.setVertices(node);
            while (text[x] != null) {
                if (dataBase.ht.containsKey(text[x])) {
                    grafo = dataBase.ht.get(text[x]);
                    grafo.setVertices(node);
                    dataBase.ht.put(text[x], grafo);
                    x++;
                    continue;
                }
                dataBase.ht.put(text[x], grafo);
                x++;
            }
        }
        for (Ways edge : dataBase.edges) {
            Set<String[]> a = edge.osmWay.keySet();
            String[] text = new String[0];
            for (String[] key : a) text = key;
            int x = 0;

            ArrayList<Nodes> vertice = new ArrayList<>();
            ArrayList<Ways> edges = new ArrayList<>();
            Grafo newGrafo = new Grafo(edges, vertice);
            newGrafo.setEdges(edge);
            while (text[x] != null) {
                if (dataBase.ht.containsKey(text[x])) {
                    newGrafo = dataBase.ht.get(text[x]);
                    newGrafo.setEdges(edge);
                    dataBase.ht.put(text[x], newGrafo);
                    x++;
                    continue;
                }
                dataBase.ht.put(text[x], newGrafo);
                x++;
            }
        }
    }
}

