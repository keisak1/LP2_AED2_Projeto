package com.projeto;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Hashtable;

public class Main {

    static DataBase dataBase = new DataBase();

    public static void main(String[] args) {

        loadFromFiles();
    }


    private static void loadFromFiles() {
        String fileSource = "C:\\Users\\Nuno\\IdeaProjects\\Projeto_LP2_AED2\\data\\dataset1_nodes.txt";
        String fileSource2 = "C:\\Users\\Nuno\\IdeaProjects\\Projeto_LP2_AED2\\data\\dataset1_ways_nodepairs.txt";
        String fileSource3 = "";
        String fileSource4 = "";
        loadFromFileNodes(fileSource);
        loadFromFileWays(fileSource2);
        loadFromFileUser(fileSource3);
        loadFromFilePoI(fileSource4);
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

    private static void loadFromFileUser(String path) {

    }

    private static void loadFromFilePoI(String path) {

    }

}
