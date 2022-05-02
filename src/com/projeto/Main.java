package com.projeto;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;

public class Main {

    static DataBase dataBase = new DataBase();
    private static String[] value;

    public static void main(String[] args) {
        loadFromFiles();
        dataBase.printBST();
    }


    private static void loadFromFiles() {
        String fileSource1 = "data\\dataset1_nodes.txt";
        String fileSource2 = "data\\dataset1_ways_nodepairs.txt";
        String fileSource3 = "data\\dataset1_pois.txt";
        String fileSource4 = "data\\dataset1_users.txt";
        loadFromFileNodes(fileSource1);
        loadFromFileWays(fileSource2);
        loadFromFilePoI(fileSource3);
        loadFromFileUser(fileSource4);
    }


    private static void loadFromFileNodes(String path) {
        In in = new In(path);
        in.readLine();
        long id;
        String[] value;
        String[] tag;
        float coordenateX;
        float coordenateY;
        while (!in.isEmpty()) {
            String[] text = in.readLine().split(",");
            id = Long.parseLong(text[0]);
            coordenateX = Float.parseFloat(text[1]);
            coordenateY = Float.parseFloat(text[2]);
            tag = new String[10];
            value = new String[10];
            int tagNumb = text.length - 3;
            int i = 0, u = 3;
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
            Nodes node = new Nodes(osm, id, coordinate, PoIArrayList, waysArrayList);
            dataBase.bst.put(id, node);
        }
    }

    private static void loadFromFileWays(String path) {
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String address = null, address2 = null, address3 = null;
            String[] text = in.readLine().split(",");
            long id = Long.parseLong(text[0]);
            long nodeID1 = Long.parseLong(text[1]);
            long nodeID2 = Long.parseLong(text[2]);
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
                    }
                    case "addr:street" -> {
                        address2 = text[u + 1];
                        u += 2;
                        tagNumb -= 2;
                    }
                    case "addr:postcode" -> {
                        address3 = text[u + 1];
                        u += 2;
                        tagNumb -= 2;
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
            Ways way = new Ways(nodeID1, nodeID2, weight, id, osmWay, address2, address, address3);
            dataBase.edges.add(way);
            Nodes node = dataBase.bst.get(nodeID1);
            node.setWays(way);
            node = dataBase.bst.get(nodeID2);
            node.setWays(way);
        }
    }

    private static void loadFromFilePoI(String path) {
        In in = new In(path);
        in.readLine();

    }

    private static void loadFromFileUser(String path) {
        In in = new In(path);
        in.readLine();
        long nodeID;
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
                nodeID = Long.parseLong(nodesVisited[1]);
                poiID = Integer.parseInt(nodesVisited[2]);
                dateNodeVisited = nodesVisited[3].split("-");
                Date nodeVisitedDate = new Date(Integer.parseInt(dateNodeVisited[0]), Integer.parseInt(dateNodeVisited[1]), Integer.parseInt(dateNodeVisited[2]));
                Nodes node = dataBase.bst.get(nodeID);
                for (PoI poi1 : node.getPoI()) {
                    if (poi1 == node.poI.get(poiID)) {
                        poI.add(poi1);
                    }
                }
                NodeVisited nodeVisited = new NodeVisited(nodeID, poiID, nodeVisitedDate, poI);
                visitedNodes.add(nodeVisited);
                u++;
                tagNumb--;
            }
            Vehicle vehicle1 = new Vehicle();
            Date bday = new Date(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));
            User user = new User(userID, name, bday, vehicle1, visitedNodes);
        }
    }
}
