package com.projeto;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

import java.util.*;


public class Main {

    static DataBase dataBase = new DataBase();

    public static void main(String[] args) {
        loadFromFiles();
        now();
        Date d = new Date(17, 11, 2032);
        dataBase.tagFound("crossing");
        dataBase.top5Users(d);
        dataBase.top5PoIs(d);
        dataBase.notVisitedPoI(d);
        loadToFiles();
    }

    /**
     * Prints all database on the instant of the calling method
     */
    static Date d = new Date(29, 4, 2022);

    private static void now() {
        Date d2 = new Date(29, 4, 2022);
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
        dataBase.overPopulatedNode(d2);
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
            node.getPoI().add(poi);
            dataBase.bst.put(nodeID, node);
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

        while (!in.isEmpty()) {
            ArrayList<PoI> poI = new ArrayList<>();
            ArrayList<NodeVisited> visitedNodes = new ArrayList<>();
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
                    if (node.getPoI().contains(node.getPoI().get(node.getPoI().indexOf(poi1)))) {
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

    private static void loadToFiles() {
        String fileSource1 = "data\\overpopulated_nodes.txt";
        String fileSource2 = "data\\graph.txt";
        String fileSource3 = "data\\nodes_visit.txt";
        String fileSource4 = "data\\top5s.txt";
        loadOverpopulatedNodesToFile(fileSource1, 19, 22);
        loadGraphToFile(fileSource2);
        loadNodesVisitToFile(fileSource3);
        loadTop5sToFile(fileSource4);
    }

    public static void loadOverpopulatedNodesToFile(String path, int from, int to) {
        Out out = new Out(path);
        Iterator<Nodes> itr = dataBase.set.iterator();
        out.println("At this date: " + d.toString() + " there were these overpopulated nodes:");
        while (itr.hasNext()) {
            Nodes node = (Nodes) itr.next();
            out.println("-Node id: " + node.getId());
        }
        dataBase.shortestPathNotOverpopulated(from, to);
        out.println();
        if (dataBase.sp.hasPathTo(to)) {
            out.println(dataBase.sp.pathTo(to));
            out.println("Shortest non overpopulated path from vertex " + from + " to vertex " + to + " is " + dataBase.sp.distTo(to));
        } else {
            out.println("There's no such path or there's no non overpopulated route.");
        }

        out.println();
        dataBase.shortestPath(from, to);

        if (dataBase.sp.hasPathTo(to)) {
            out.println(dataBase.sp.pathTo(to));
            out.println("Shortest path from vertex " + from + " to vertex " + to + " is " + dataBase.sp.distTo(to));
        } else {
            out.println("There's no such path.");
        }
    }

    public static void loadGraphToFile(String path) {
        Out out = new Out(path);
        out.println("Check graph connectivity data:");
        if(dataBase.checkConnectivity()==0){
            out.println("The graph is connected.");
        }else{
            out.println("The graph isn't connected.");
        }
        out.println();
        out.println("Check subgraph connectivity data:");

        if(dataBase.checkSubGraphConnectivity()==0){
            out.println("The subgraph is connected.");
        }else{
            out.println("The subgrap isn't connected.");
        }
    }

    public static void loadNodesVisitToFile(String path) {
        Out out = new Out(path);
        ArrayList<PoI> visitedPoI = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        out.println("At this date: " + d + " these points of interest were visited by a user:");
        for (User user : dataBase.users) {
            visitedPoI = dataBase.visitedBy(d, user);
            out.println("-" + user.getName());
            for (PoI poI : visitedPoI) {
                out.println("---PoI name: " + poI.getName());
            }
        }
        out.println("At this date: " + d + " these points of interest were not visited by any user:");
        Set<String> notVisited = new HashSet<>();
        for (User user : dataBase.users) {
            dataBase.visitedBy(d, user);
            for (Integer i : dataBase.bst.keys()) {
                Nodes node = dataBase.bst.get(i);
                for (PoI poI : node.getPoI()) {
                    if (!dataBase.visitedBy(d, user).contains(poI)) {
                        notVisited.add(poI.getName());

                    }
                }
            }
        }

        for (String s : notVisited) {
            out.println("-PoI: " + s);
        }

        out.println("At this date: " + d + " these users visited a point of interest:");
        for (Integer nodeKey : dataBase.bst.keys()) {
            for (PoI poi : dataBase.searchNode(nodeKey).getPoI()) {
                out.println("-PoI:" + poi.getName());
                users = dataBase.whoVisited(d, poi);
                for (User user : users) {
                    out.println("-PoI:" + poi.getName());
                    out.println("---User id: " + user.getId());
                }
            }
        }
        out.println("At this date: " + d + " these points of interest were not visited:");
        for (Integer nodeKey : dataBase.bst.keys()) {
            for (PoI poi : dataBase.searchNode(nodeKey).getPoI()) {
                out.println("-PoI id: " + poi.getId());
            }
        }
    }

    public static void loadTop5sToFile(String path) {
        Out out = new Out(path);
        ArrayList<User> top5users;
        ArrayList<PoI> top5pois;

        out.println("At this date: " + d + " these are the top 5 users that visited most points of interest:");
        top5users = dataBase.top5Users(d);
        for (User user : top5users) {
            out.println("-User id: " + user.getId());
        }

        out.println("At this date: " + d + " these are the top 5 points of interest most visited:");
        top5pois = dataBase.top5PoIs(d);
        for (PoI poI : top5pois) {
            out.println("-PoI id: " + poI.getId());
        }
    }
}