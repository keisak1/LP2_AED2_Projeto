package com.projeto;


import edu.princeton.cs.algs4.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.lang.System.exit;
import static java.lang.System.out;


/**
 * The type Data base.
 */
public class DataBase implements Initializable {


    public TextField textfieldName;
    public DatePicker Birthday;
    public TextField textfieldVehicle;
    public Button addBtn;
    public Button editBtn;
    public Button removeBtn;

    //Basic Information tab
    public TableView<User> tableView;
    public TableColumn<User, Integer> colID;
    public TableColumn<User, String> colName;
    public TableColumn<User, Date> colBirthday;
    public TableColumn<User, String> colVehicle;
    public TextField searchFieldName;
    public Button searchBtn;

    //DateVisited tab
    public TableView<NodeVisited> tableViewNodesVisited;
    public TableColumn<NodeVisited, Integer> userID;
    public TableColumn<Nodes, Integer> nodeID;
    public TableColumn<NodeVisited, Date> dateVisited;

    //PoI tab
    public TableColumn<Nodes, Integer> poiNodeID;
    public TableColumn<PoI, Integer> poiID;
    public TableColumn<PoI, String> poiName;
    public TableColumn<PoI, String> poiVehicle;
    public TableView<PoI> tableViewPoI;
    public TextField NodeVisitedField;
    public DatePicker DateVisitedField;

    //Nodes tab
    public TableView<Nodes> tableViewNodes;
    public TableColumn<Nodes, Integer> ID;
    public TableColumn<Nodes, Integer> vertexID;
    public TableColumn<Nodes, Coordinate> coordinates;
    public TableColumn<Nodes, PoI> poi;
    public TableColumn<Nodes, Ways> ways;
    public TextField nodeidtextfield;
    public TextField vertexIdTextField;
    public TextField coordinateXTextField;
    public Button addNodeBtn;
    public Button editNodeBtn;
    public Button removeNodeBtn;
    public TextField coordinateYTextField;
    public TextField poiTextField;
    public TextField waysTextField;
    public TextField searchFieldNode;
    public Button searchNodeBtn;
    public TextField valueTextField;
    public TextField tagTextField;
    public TableView<Nodes> tableViewTag;
    public TableColumn<Nodes, Integer> tagNodeID;
    public TableColumn<Nodes, String> tagCol;
    public TableColumn<Nodes, String> valueCol;
    // Node - PoI tab
    public TableView<PoI> tableViewPoI2;
    public TableColumn<PoI, Integer> colPOI;
    public TableColumn<PoI, Integer> colNodeID;
    public TableColumn<PoI, String> colPOIName;
    public TableColumn<PoI, String> colVehiclePOI;
    public Group graphGroup;
    public Button graphBtn;
    public Button shortestPathBtn;
    public TextField fromID;
    public TextField toID;
    public DatePicker shortDate;

    // Ways
    public TableView<Ways> tableViewNodes1;
    public TableColumn<Ways, Integer> wayID;
    public TableColumn<Ways, Integer> wayNodeV;
    public TableColumn<Ways, Integer> wayNodeW;
    public TableColumn<Ways, Double> wayWeight;
    public TextField wayIdTextField;
    public TextField wayNodeVTextField;
    public TextField wayNodeWTextField;
    public Button addWayBtn;
    public Button EditWayBtn;
    public Button removeWayBtn;
    public TextField wayWeightTextField;
    public TextField tagWayTextField;
    public TextField valueWayTextField;
    public TextField searchFieldWay;
    public Button searchWayBtn;
    public TextField addressTextField;
    public TextField PostCodeTextField;
    public TableColumn<Ways, String> wayAddress;
    public TableColumn<Ways, String> wayPostCode;
    public TableView<Ways> tableViewTag1;
    public TableColumn<Ways, Integer> wayTagId;
    public TableColumn<Ways, String> wayTagCol;
    public TextField wayNameTextField;
    public TableColumn<Ways, String> wayNameColumn;


    ObservableList<PoI> observablePoiList2 = FXCollections.observableArrayList();
    ObservableList<User> observableUserList = FXCollections.observableArrayList();
    ObservableList<NodeVisited> observableNodeVisitedList = FXCollections.observableArrayList();
    ObservableList<PoI> observablePoiList = FXCollections.observableArrayList();
    ObservableList<Nodes> observableNodeList = FXCollections.observableArrayList();
    ObservableList<Ways> observableWaysList = FXCollections.observableArrayList();

    private static final String fileSource1 = "data\\dataset1_nodes.txt";
    private static final String fileSource2 = "data\\dataset1_ways_nodepairs.txt";
    private static final String fileSource3 = "data\\dataset1_pois.txt";
    private static final String fileSource4 = "data\\dataset1_users.txt";
    private static final String fileSource5 = "data\\dataset1_nodes_bin.bin";
    private static final String fileSource6 = "data\\dataset1_ways_nodepairs_bin.bin";
    private static final String fileSource7 = "data\\dataset1_pois_bin.bin";
    private static final String fileSource8 = "data\\datasete1_users_bin.bin";

    public Integer usersSize;

    public void setUsersSize(Integer usersSize) {
        this.usersSize = usersSize;
    }

    public Integer setUsersSize() {
        return usersSize;
    }

    /**
     * The Bst.
     */
    public static BinarySearchST<Integer, Nodes> bst = new BinarySearchST<>();
    /**
     * The Ht.
     */
    public static Hashtable<String, Grafo> ht = new Hashtable<>();
    /**
     * The Users.
     */

    public ArrayList<User> users = new ArrayList<>();
    /**
     * The Edges.
     */
    public static ArrayList<Ways> edges = new ArrayList<>();
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
        ewg = new EdgeWeightedDigraph(getBstSize() + 1);
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
                if (dfs.marked(v)) StdOut.print(v + " ");
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
                if (dfs.marked(v)) StdOut.print(v + " ");
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
                users.set(i + 1, user);
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
        users.removeIf(user -> user.id == userID);
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
        for (Entry<String, Integer> map : sortedMap.entrySet()) {
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
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0 ? o2.getKey().compareTo(o1.getKey()) : o2.getValue().compareTo(o1.getValue()));
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
                        for (Entry<String, Integer> map : tempMap.entrySet()) {
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
        for (Entry<String, Integer> map : sortedMap.entrySet()) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        colVehicle.setCellValueFactory(new PropertyValueFactory<>("Vehicle"));
        userID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        nodeID.setCellValueFactory(new PropertyValueFactory<>("nodeID"));
        dateVisited.setCellValueFactory(new PropertyValueFactory<>("dateVisited"));
        poiID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        poiName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        poiNodeID.setCellValueFactory(new PropertyValueFactory<>("nodeID"));
        poiVehicle.setCellValueFactory(new PropertyValueFactory<>("Type"));
        ID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        vertexID.setCellValueFactory(new PropertyValueFactory<>("VertexID"));
        coordinates.setCellValueFactory(new PropertyValueFactory<>("Point"));
        ways.setCellValueFactory(new PropertyValueFactory<>("Ways"));
        poi.setCellValueFactory(new PropertyValueFactory<>("PoI"));
        tagNodeID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tagCol.setCellValueFactory(new PropertyValueFactory<>("OsmNode"));
        colPOI.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colNodeID.setCellValueFactory(new PropertyValueFactory<>("NodeID"));
        colPOIName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colVehiclePOI.setCellValueFactory(new PropertyValueFactory<>("Type"));
        wayID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        wayNodeV.setCellValueFactory(new PropertyValueFactory<>("V"));
        wayNodeW.setCellValueFactory(new PropertyValueFactory<>("W"));
        wayWeight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        wayPostCode.setCellValueFactory(new PropertyValueFactory<>("Postcode"));
        wayTagId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        wayTagCol.setCellValueFactory(new PropertyValueFactory<>("OsmWay"));
        wayNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    }

    private void loadFromFileNodes(String path) {
        int size = 0;
        In in = new In(path);
        in.readLine();
        int id;
        int vertexID;
        String value;
        String tag;
        float coordenateX;
        float coordenateY;
        while (!in.isEmpty()) {
            String[] text = in.readLine().split(",");
            id = Integer.parseInt(text[0]);
            coordenateX = Float.parseFloat(text[1]);
            coordenateY = Float.parseFloat(text[2]);
            vertexID = Integer.parseInt(text[3]);
            int tagNumb = text.length - 4;
            int i = 0, u = 4;
            Hashtable<String, String> osm = new Hashtable<>();
            while (tagNumb != 0) {
                tag = text[u];
                value = text[u + 1];
                tagNumb -= 2;
                i++;
                u += 2;
                osm.put(tag, value);
            }
            Coordinate coordinate = new Coordinate(coordenateX, coordenateY);
            ArrayList<Ways> waysArrayList = new ArrayList<>();
            ArrayList<PoI> PoIArrayList = new ArrayList<>();
            Nodes node = new Nodes(osm, id, vertexID, coordinate, PoIArrayList, waysArrayList);
            bst.put(id, node);
            setBstSize(size++);
        }
    }

    /**
     * Loads content from file path to Ways database
     *
     * @param path - String
     */
    private void loadFromFileWays(String path) {
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
            Hashtable<String, String> osmWay = new Hashtable<>();
            String value = "";
            String tag = "";
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

                tag = text[u];
                value = text[u + 1];
                tagNumb -= 2;
                i++;
                u += 2;
                osmWay.put(tag, value);
            }

            Nodes node = bst.get(nodeID1);
            Nodes node2 = bst.get(nodeID2);
            Ways way = new Ways(node.getVertexID(), node2.getVertexID(), weight, id, osmWay, address2, address, address3);
            edges.add(way);
            node2.setWays(way);
            node.setWays(way);
            setEdgesSize(size++);
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
        while (!in.isEmpty()) {
            String[] text = in.readLine().split(",");
            poiID = Integer.parseInt(text[0]);
            nodeID = Integer.parseInt(text[1]);
            name = text[2];
            Vehicle = text[3];

            PoI poi = new PoI(poiID, nodeID, name, Vehicle);
            Nodes node = bst.get(nodeID);
            node.getPoI().add(poi);
            bst.put(nodeID, node);
        }
    }

    /**
     * Loads content from file path to User database
     *
     * @param path - String
     */
    private void loadFromFileUser(String path) {
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
                Nodes node = bst.get(nodeID);
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
            Date bday = new Date(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));
            User user = new User(userID, name, bday, vehicle, visitedNodes);
            addUser(user);
        }
    }

    /**
     * Fills HashTable from database with Nodes and Ways tags
     */
    private static void fillHash() {
        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            Set<String> a = node.osmNode.keySet();
            String text = null;
            for (String key : a) text = key;
            int x = 0;
            ArrayList<Nodes> vertice = new ArrayList<>();
            ArrayList<Ways> edges = new ArrayList<>();
            Grafo grafo = new Grafo(edges, vertice);
            grafo.setVertices(node);
            if (text != null && ht.containsKey(text)) {
                grafo = ht.get(text);
                grafo.setVertices(node);
                ht.put(text, grafo);
            }
            if (text != null) {
                ht.put(text, grafo);
            }
        }

        for (Ways edge : edges) {
            Set<String> a = edge.osmWay.keySet();
            String text = "";
            for (String key : a) text = key;
            int x = 0;

            ArrayList<Nodes> vertice = new ArrayList<>();
            ArrayList<Ways> edges = new ArrayList<>();
            Grafo newGrafo = new Grafo(edges, vertice);
            newGrafo.setEdges(edge);
            if (ht.containsKey(text)) {
                newGrafo = ht.get(text);
                newGrafo.setEdges(edge);
                ht.put(text, newGrafo);
                x++;
                continue;
            }
            ht.put(text, newGrafo);
            x++;

        }
    }


    private void clearTableItems() {
        observableUserList.removeAll();

    }

    public void handleReadTextFileAction(ActionEvent actionEvent) {
        clearTableItems();

        try {
            loadFromFileNodes(fileSource1);
            loadFromFileWays(fileSource2);
            loadFromFilePoI(fileSource3);
            loadFromFileUser(fileSource4);
            fillHash();
        } catch (Exception e) {
            e.printStackTrace();
        }

        afterReadFileAction();
    }

    private void afterReadFileAction() {

        observableUserList.removeAll();
        observableUserList.addAll(users);
        tableView.setItems(observableUserList);
        for (User user : users) {
            for (NodeVisited nodeVisited : user.getNodesVisited()) {
                observableNodeVisitedList.add(nodeVisited);
                for (PoI poi : nodeVisited.getPoI()) {
                    if (!observablePoiList.contains(poi)) {
                        observablePoiList.add(poi);
                    }
                }
            }
        }

        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            observableNodeList.add(node);
            for (PoI poi : node.getPoI()) {
                if (!observablePoiList2.contains(poi)) {
                    observablePoiList2.add(poi);
                }
            }
        }
        observableWaysList.addAll(edges);

        tableViewNodes.setItems(observableNodeList);
        tableViewTag.setItems(observableNodeList);
        tableViewNodesVisited.setItems(observableNodeVisitedList);
        tableViewPoI.setItems(observablePoiList);
        tableViewPoI2.setItems(observablePoiList2);
        tableViewNodes1.setItems(observableWaysList);
        tableViewTag1.setItems(observableWaysList);

    }

    public void addUserBtn(ActionEvent actionEvent) {
        try {
            String name = textfieldName.getText();
            int day = Birthday.getValue().getDayOfMonth();
            int month = Birthday.getValue().getMonthValue();
            int year = Birthday.getValue().getYear();
            int id = users.size() + 1;
            String Vehicle = textfieldVehicle.getText();
            Date d = new Date(day, month, year);
            ArrayList<NodeVisited> nodeVisitedArrayList = new ArrayList<>();
            String visitedField = NodeVisitedField.getText();
            day = DateVisitedField.getValue().getDayOfMonth();
            month = DateVisitedField.getValue().getMonthValue();
            year = DateVisitedField.getValue().getYear();
            Date d2 = new Date(day, month, year);
            String[] text = visitedField.split(",");
            int length = text.length;
            ArrayList<PoI> poi = new ArrayList<>();
            while (length != 0) {
                String s = text[length - 1];
                int nodeid = Integer.parseInt(s);
                for (Integer x : bst.keys()) {
                    Nodes node = bst.get(x);

                    if (node.getId() == nodeid) {
                        poi.addAll(node.getPoI());
                    }
                }
                NodeVisited nodeVisited = new NodeVisited(nodeid, d2, poi);
                nodeVisitedArrayList.add(nodeVisited);
                length--;
            }
            User user = new User(id, name, d, Vehicle, nodeVisitedArrayList);

            users.add(user);
            tableView.getItems().add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editUserBtn(ActionEvent actionEvent) {
        try {
            String name = textfieldName.getText();
            Iterator<User> u = users.iterator();
            String Vehicle = textfieldVehicle.getText();
            int i = 0;
            while (u.hasNext()) {
                if (u.next().getName().equals(name)) {
                    i = u.next().getId();
                }
            }
            User editedUser = users.get(i - 2);
            int day = Birthday.getValue().getDayOfMonth();
            int month = Birthday.getValue().getMonthValue();
            int year = Birthday.getValue().getYear();
            Date d = new Date(day, month, year);
            editedUser.setBirthday(d);
            ArrayList<NodeVisited> nodeVisitedArrayList = new ArrayList<>();
            String visitedField = NodeVisitedField.getText();
            day = DateVisitedField.getValue().getDayOfMonth();
            month = DateVisitedField.getValue().getMonthValue();
            year = DateVisitedField.getValue().getYear();
            Date d2 = new Date(day, month, year);
            String[] text = visitedField.split(",");
            int length = text.length;
            ArrayList<PoI> poi = new ArrayList<>();
            while (length != 0) {
                String s = text[length - 1];
                int nodeid = Integer.parseInt(s);
                for (Integer x : bst.keys()) {
                    Nodes node = bst.get(x);

                    if (node.getId() == nodeid) {
                        poi.addAll(node.getPoI());
                    }
                }
                NodeVisited nodeVisited = new NodeVisited(nodeid, d2, poi);
                nodeVisitedArrayList.add(nodeVisited);
                length--;
            }
            editedUser.setNodesVisited(nodeVisitedArrayList);
            editedUser.setVehicle(Vehicle);

            tableView.getItems().clear();
            observableUserList.removeAll();
            observableUserList.addAll(users);
        } catch (Exception e) {
            System.out.println("There's no such user to be edited");
        }
    }

    public void removeUserBtn(ActionEvent actionEvent) {
        Integer id = tableView.getSelectionModel().getSelectedItem().getId();

        deleteUser(id);
        tableView.getItems().clear();
        afterReadFileAction();
    }

    public void handleSaveBinFileAction(ActionEvent actionEvent) {
        DataOutputStream dos = null;
        DataOutputStream dos1 = null;
        DataOutputStream dos2 = null;
        DataOutputStream dos3 = null;

        try {
            dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileSource5)));
            dos1 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileSource6)));
            dos2 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileSource7)));
            dos3 = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileSource8)));

            dos.writeInt(bstSize);
            dos.writeChars("\n");
            for (Integer i : bst.keys()) {
                Nodes node = bst.get(i);
                String msg = node.getId() + "," + node.getPoint().getX() + "," + node.getPoint().getY() + "," + node.getVertexID();
                dos.writeChars(msg);
                Enumeration<String> e = node.osmNode.keys();
                while (e.hasMoreElements()) {
                    String tag = e.nextElement();
                    String msg1 = "," + tag + ":" + node.osmNode.get(tag);
                    dos.writeChars(msg1);
                }
                dos.writeChars("\n");
            }

            dos1.writeInt(edgesSize);
            dos1.writeChars("\n");
            int nodeID1 = 0, nodeID2 = 0;
            for (Ways way : edges) {
                int vertexIDNode1 = way.getV(), vertexIDNode2 = way.getW();
                for (Integer j : bst.keys()) {
                    Nodes node1 = bst.get(j);
                    if (node1.getVertexID() == vertexIDNode1) {
                        nodeID1 = node1.getId();
                    }
                    if (node1.getVertexID() == vertexIDNode2) {
                        nodeID2 = node1.getId();
                    }
                }
                String msg2 = way.getId() + "," + nodeID1 + "," + nodeID2 + "," + way.getWeight();
                dos1.writeChars(msg2);
                Enumeration<String> en = way.osmWay.keys();
                while (en.hasMoreElements()) {
                    String tag1 = en.nextElement();
                    if (tag1 != null && way.osmWay.get(tag1) != null) {
                        String msg3 = "," + tag1 + ":" + way.osmWay.get(tag1);
                        dos1.writeChars(msg3);
                    }
                }
                dos1.writeChars("\n");
            }

            int size = 0;
            for (Integer m : bst.keys()) {
                Nodes node2 = bst.get(m);
                size += node2.getPoI().size();
            }
            dos2.writeInt(size);
            for (Integer n : bst.keys()) {
                Nodes node3 = bst.get(n);
                for (PoI poI : node3.getPoI()) {
                    String msg4 = poI.getId() + "," + node3.getId() + "," + poI.getName() + "," + poI.getType();
                    dos2.writeChars(msg4);
                    dos2.writeChars("\n");
                }
            }

            dos3.writeInt(users.size());
            for (User user : users) {
                String msg5 = user.getId() + "," + user.getName() + "," + user.getBirthday() + "," + user.getVehicle();
                dos3.writeChars(msg5);
                for (NodeVisited nodeVisited : user.getNodesVisited()) {
                    for (PoI poi : nodeVisited.getPoI()) {
                        String msg6 = "Visited: " + nodeVisited.getNodeID() + ":" + poi.getId() + ":" + nodeVisited.getDateVisited().toString();
                        dos3.writeChars(msg6);
                    }
                }
                dos3.writeChars("\n");
            }
        } catch (Exception e) {
            StdOut.println(e);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (dos1 != null) {
                    dos1.close();
                }
                if (dos2 != null) {
                    dos2.close();
                }
                if (dos3 != null) {
                    dos3.close();
                }
            } catch (IOException ioex) {
                StdOut.println(ioex);
            }
        }
    }

    public void handleSaveTextFileAction(ActionEvent actionEvent) {
        Out out = new Out(fileSource1);
        Out out1 = new Out(fileSource2);
        Out out2 = new Out(fileSource3);
        Out out3 = new Out(fileSource4);

        out.println(bstSize);
        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            out.print(node.getId() + "," + node.getPoint().getX() + "," + node.getPoint().getY() + "," + node.getVertexID());
            Enumeration<String> e = node.osmNode.keys();
            while (e.hasMoreElements()) {
                String tag = e.nextElement();
                out.print("," + tag + "," + node.osmNode.get(tag));
            }
            out.println();
        }

        out1.println(edgesSize);
        int nodeID1 = 0, nodeID2 = 0;
        for (Ways way : edges) {
            int vertexIDNode1 = way.getV(), vertexIDNode2 = way.getW();
            for (Integer j : bst.keys()) {
                Nodes node1 = bst.get(j);
                if (node1.getVertexID() == vertexIDNode1) {
                    nodeID1 = node1.getId();
                }
                if (node1.getVertexID() == vertexIDNode2) {
                    nodeID2 = node1.getId();
                }
            }
            out1.print(way.getId() + "," + nodeID1 + "," + nodeID2 + "," + way.getWeight());
            Enumeration<String> en = way.osmWay.keys();
            while (en.hasMoreElements()) {
                String tag1 = en.nextElement();
                if (tag1 != null && way.osmWay.get(tag1) != null) {
                    out1.print("," + tag1 + "," + way.osmWay.get(tag1));
                }
            }
            out1.println();
        }

        int size = 0;
        for (Integer m : bst.keys()) {
            Nodes node2 = bst.get(m);
            size += node2.getPoI().size();
        }
        out2.println(size);
        for (Integer n : bst.keys()) {
            Nodes node3 = bst.get(n);
            for (PoI poI : node3.getPoI()) {
                out2.println(poI.getId() + "," + node3.getId() + "," + poI.getName() + "," + poI.getType());
            }
        }

        out3.println(users.size());
        for (User user : users) {
            out3.print(user.getId() + "," + user.getName() + "," + user.getBirthday() + "," + user.getVehicle());
            for (NodeVisited nodeVisited : user.getNodesVisited()) {
                for (PoI poi : nodeVisited.getPoI()) {
                    out3.print("," + "Visited:" + poi.getNodeID() + ":" + poi.getId() + ":" + nodeVisited.getDateVisited().toString());
                }
            }
            out3.println();
        }
        Date d = new Date(29, 4, 2022);
        loadOverpopulatedNodesToFile("data\\overpopulated_nodes_file_txt.txt", 19, 22, d);
        loadGraphToFile("data\\graph_file_txt.txt", d);
        loadNodesVisitToFile("data\\nodes_visit_file_txt.txt", d);
        loadTop5sToFile("data\\top5s_file_txt.txt", d);
    }

    public void loadOverpopulatedNodesToFile(String path, int from, int to, Date d) {
        Out out = new Out(path);
        Iterator<Nodes> itr = set.iterator();
        out.println("At this date: " + d.toString() + " there were these overpopulated nodes:");
        while (itr.hasNext()) {
            Nodes node = (Nodes) itr.next();
            out.println("-Node id: " + node.getId());
        }
        shortestPathNotOverpopulated(from, to);
        out.println();
        if (sp.hasPathTo(to)) {
            out.println(sp.pathTo(to));
            out.println("Shortest non overpopulated path from vertex " + from + " to vertex " + to + " is " + sp.distTo(to));
        } else {
            out.println("There's no such path or there's no non overpopulated route.");
        }

        out.println();
        shortestPath(from, to);

        if (sp.hasPathTo(to)) {
            out.println(sp.pathTo(to));
            out.println("Shortest path from vertex " + from + " to vertex " + to + " is " + sp.distTo(to));
        } else {
            out.println("There's no such path.");
        }
    }

    public void loadGraphToFile(String path, Date d) {
        Out out = new Out(path);
        out.println("Check graph connectivity data:");
        if (checkConnectivity() == 0) {
            out.println("The graph is connected.");
        } else {
            out.println("The graph isn't connected.");
        }
        out.println();
        out.println("Check subgraph connectivity data:");

        if (checkSubGraphConnectivity() == 0) {
            out.println("The subgraph is connected.");
        } else {
            out.println("The subgrap isn't connected.");
        }
    }

    public void loadNodesVisitToFile(String path, Date d) {
        Out out = new Out(path);
        ArrayList<PoI> visitedPoI = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        out.println("At this date: " + d + " these points of interest were visited by a user:");
        for (User user : users) {
            visitedPoI = visitedBy(d, user);
            out.println("-" + user.getName());
            for (PoI poI : visitedPoI) {
                out.println("---PoI name: " + poI.getName());
            }
        }
        out.println("At this date: " + d + " these points of interest were not visited by any user:");
        Set<String> notVisited = new HashSet<>();
        for (User user : users) {
            visitedBy(d, user);
            for (Integer i : bst.keys()) {
                Nodes node = bst.get(i);
                for (PoI poI : node.getPoI()) {
                    if (!visitedBy(d, user).contains(poI)) {
                        notVisited.add(poI.getName());

                    }
                }
            }
        }

        for (String s : notVisited) {
            out.println("-PoI: " + s);
        }

        out.println("At this date: " + d + " these users visited a point of interest:");
        for (Integer nodeKey : bst.keys()) {
            for (PoI poi : searchNode(nodeKey).getPoI()) {
                out.println("-PoI:" + poi.getName());
                users = whoVisited(d, poi);
                for (User user : users) {
                    out.println("-PoI:" + poi.getName());
                    out.println("---User id: " + user.getId());
                }
            }
        }
        out.println("At this date: " + d + " these points of interest were not visited:");
        for (Integer nodeKey : bst.keys()) {
            for (PoI poi : searchNode(nodeKey).getPoI()) {
                out.println("-PoI id: " + poi.getId());
            }
        }
    }

    public void loadTop5sToFile(String path, Date d) {
        Out out = new Out(path);
        ArrayList<User> top5users;
        ArrayList<PoI> top5pois;

        out.println("At this date: " + d + " these are the top 5 users that visited most points of interest:");
        top5users = top5Users(d);
        for (User user : top5users) {
            out.println("-User id: " + user.getId());
        }

        out.println("At this date: " + d + " these are the top 5 points of interest most visited:");
        top5pois = top5PoIs(d);
        for (PoI poI : top5pois) {
            out.println("-PoI id: " + poI.getId());
        }
    }

    public void handleExitAction(ActionEvent actionEvent) {
    }

    public void handleReadBinFileAction(ActionEvent actionEvent) {
        DataInputStream dis = null;
        DataInputStream dis1 = null;
        DataInputStream dis2 = null;
        DataInputStream dis3 = null;

        try {
            dis = new DataInputStream(new BufferedInputStream(new FileInputStream(fileSource5)));
            dis1 = new DataInputStream(new BufferedInputStream(new FileInputStream(fileSource6)));
            dis2 = new DataInputStream(new BufferedInputStream(new FileInputStream(fileSource7)));
            dis3 = new DataInputStream(new BufferedInputStream(new FileInputStream(fileSource8)));
            byte[] buffer = new byte[1000];
            byte[] buffer1 = new byte[1000];
            byte[] buffer2 = new byte[1000];
            byte[] buffer3 = new byte[1000];


            int nRead = 0, size = 0, id, vertexID;
            String tag = null;
            String value = null;
            float coordenateX;
            float coordenateY;
            bstSize = dis.readInt();
            while ((nRead = dis.read(buffer)) != -1) {
                String line = new String(buffer);
                String[] text = line.split(",");
                String test = text[0].replace("\n", "");
                id = Integer.parseInt(test);
                coordenateX = Float.parseFloat(text[1]);
                coordenateY = Float.parseFloat(text[2]);
                vertexID = Integer.parseInt(text[3]);
                int tagNumb = text.length - 4;
                int i = 0, u = 4;
                while (tagNumb != 0) {
                    tag = text[u];
                    value = text[u + 1];
                    tagNumb -= 2;
                    i++;
                    u += 2;
                }
                Hashtable<String, String> osm = new Hashtable<>();
                osm.put(tag, value);
                Coordinate coordinate = new Coordinate(coordenateX, coordenateY);
                ArrayList<Ways> waysArrayList = new ArrayList<>();
                ArrayList<PoI> PoIArrayList = new ArrayList<>();
                Nodes node = new Nodes(osm, id, vertexID, coordinate, PoIArrayList, waysArrayList);
                bst.put(id, node);
                setBstSize(size++);
            }

            edgesSize = dis1.readInt();
            int nRead1 = 0, size1 = 0;
            while ((nRead1 = dis1.read(buffer1)) != -1) {
                String address = null, address2 = null, address3 = null;
                String line1 = new String(buffer1);
                String[] text1 = line1.split(",");
                Integer id1 = Integer.parseInt(text1[0]);
                int nodeID1 = Integer.parseInt(text1[1]);
                int nodeID2 = Integer.parseInt(text1[2]);
                float weight = Float.parseFloat(text1[3]);
                String value1 = "";
                String tag1 = "";
                int tagNumb = text1.length - 4;
                int i = 0, u = 4;
                while (tagNumb != 0) {
                    if (text1[u].equals("name")) {
                        String name = text1[u + 1];
                        u += 2;
                        tagNumb -= 2;
                    }
                    switch (text1[u]) {
                        case "addr:city" -> {
                            address = text1[u + 1];
                            u += 2;
                            tagNumb -= 2;
                            continue;
                        }
                        case "addr:street" -> {
                            address2 = text1[u + 1];
                            u += 2;
                            tagNumb -= 2;
                            continue;
                        }
                        case "addr:postcode" -> {
                            address3 = text1[u + 1];
                            u += 2;
                            tagNumb -= 2;
                            continue;
                        }
                    }
                    tag1 = text1[u];
                    value1 = text1[u + 1];
                    tagNumb -= 2;
                    i++;
                    u += 2;
                }
                Hashtable<String, String> osmWay = new Hashtable<>();
                osmWay.put(tag1, value1);
                Nodes node = bst.get(nodeID1);
                Nodes node2 = bst.get(nodeID2);
                Ways way = new Ways(node.getVertexID(), node2.getVertexID(), weight, id1, osmWay, address2, address, address3);
                edges.add(way);
                node2.setWays(way);
                node.setWays(way);
                setEdgesSize(size1++);
            }


            for (Integer i : bst.keys()) {
                Nodes nodes = bst.get(i);
                nodes.setPoISize(dis2.readInt());
            }
            int nRead2 = 0, poiID, nodeID3;
            String name, Vehicle;
            while ((nRead2 = dis2.read(buffer2)) != -1) {
                String line2 = new String(buffer2);
                String[] text2 = line2.split(",");
                poiID = Integer.parseInt(text2[0]);
                nodeID3 = Integer.parseInt(text2[1]);
                name = text2[2];
                Vehicle = text2[3];
                PoI poi = new PoI(poiID, nodeID3, name, Vehicle);
                Nodes node = bst.get(nodeID3);
                node.getPoI().add(poi);
                bst.put(nodeID3, node);
            }


            usersSize = dis3.readInt();
            int nRead3 = 0, nodeID4, tagNumb, u, poiID1, userID;
            String name1, vehicle;
            String[] bd, nodesVisited, dateNodeVisited;
            while ((nRead3 = dis3.read(buffer3)) != -1) {
                ArrayList<PoI> poI = new ArrayList<>();
                ArrayList<NodeVisited> visitedNodes = new ArrayList<>();
                String line3 = new String(buffer3);
                String[] text3 = line3.split(",");
                userID = Integer.parseInt(text3[0]);
                name1 = text3[1];
                bd = text3[2].split("-");
                vehicle = text3[3];
                tagNumb = text3.length - 4;
                u = 4;
                while (tagNumb >= 0) {
                    nodesVisited = text3[u].split(":");
                    nodeID4 = Integer.parseInt(nodesVisited[1]);
                    poiID1 = Integer.parseInt(nodesVisited[2]);
                    dateNodeVisited = nodesVisited[3].split("-");
                    Date nodeVisitedDate = new Date(Integer.parseInt(dateNodeVisited[0]), Integer.parseInt(dateNodeVisited[1]), Integer.parseInt(dateNodeVisited[2]));
                    Nodes node = bst.get(nodeID4);
                    for (PoI poi1 : node.getPoI()) {
                        if (node.getPoI().contains(node.getPoI().get(node.getPoI().indexOf(poi1)))) {
                            poI.add(poi1);
                        }
                    }
                    NodeVisited nodeVisited = new NodeVisited(nodeID4, nodeVisitedDate, poI);
                    visitedNodes.add(nodeVisited);
                    u += 2;
                    tagNumb -= 2;
                }
                Date bday = new Date(Integer.parseInt(bd[0]), Integer.parseInt(bd[1]), Integer.parseInt(bd[2]));
                User user = new User(userID, name1, bday, vehicle, visitedNodes);
                addUser(user);
            }
        } catch (Exception e) {
            StdOut.println(e);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dis1 != null) {
                    dis1.close();
                }
                if (dis2 != null) {
                    dis2.close();
                }
                if (dis3 != null) {
                    dis3.close();
                }
            } catch (IOException ioex) {
                StdOut.println(ioex);
            }
        }
    }

    public void searchUserBtn(ActionEvent actionEvent) {
        String name = searchFieldName.getText();
        try {
            for (User user : users) {
                if (user.name.equals(name)) {
                    tableView.getItems().clear();
                    tableView.getItems().add(user);
                }
            }

        } catch (Exception e) {
            System.out.println("There's no such user");
        }
    }

    public void searchNodeBtn(ActionEvent actionEvent) {
        Integer id = Integer.parseInt(searchFieldNode.getText());
        try {
            for (Integer i : bst.keys()) {
                if (Objects.equals(i, id)) {
                    Nodes node = bst.get(i);
                    tableViewNodes.getItems().clear();
                    tableViewNodes.getItems().add(node);
                }
            }
        } catch (Exception e) {
            System.out.println("There's no such node");
        }
    }

    public void removeNodeBtn(ActionEvent actionEvent) {
        Integer id = tableViewNodes.getSelectionModel().getSelectedItem().getId();
        bst.delete(id);
        tableViewNodes.getItems().clear();
        afterReadFileAction();

    }

    public void addNodeBtn(ActionEvent actionEvent) throws IOException {
        Integer id = Integer.parseInt(nodeidtextfield.getText());

        for (Integer i : bst.keys()) {
            if (id.equals(i)) {
                throw new IOException("Node already exists");
            }
        }

        Integer vertexID = Integer.parseInt(vertexIdTextField.getText());
        Coordinate coordinate = new Coordinate(Double.parseDouble(coordinateXTextField.getText()), Double.parseDouble(coordinateYTextField.getText()));
        String[] text = poiTextField.getText().split(",");
        ArrayList<PoI> poIArrayList = new ArrayList<>();
        for (String s : text) {
            for (Integer x : bst.keys()) {
                Nodes node = bst.get(x);
                for (PoI poi : node.getPoI()) {
                    if (poi.getId().equals(Integer.parseInt(s))) {
                        poIArrayList.add(poi);
                    }
                }
            }
        }
        ArrayList<Ways> waysArrayList = new ArrayList<>();
        text = waysTextField.getText().split(",");
        for (String s : text) {
            for (Ways way : edges) {
                if (Integer.parseInt(s) == way.getId()) {
                    waysArrayList.add(way);
                }
            }
        }

        Hashtable<String, String> ht = new Hashtable<>();
        ht.put(tagTextField.getText(), valueTextField.getText());
        Nodes node = new Nodes(ht, id, vertexID, coordinate, poIArrayList, waysArrayList);
        bst.put(id, node);
        tableViewNodes.getItems().add(node);
    }

    public void editNodeBtn(ActionEvent actionEvent) {
        try {
            Nodes node = bst.get(Integer.parseInt(nodeidtextfield.getText()));
            node.setVertexID(Integer.parseInt(vertexIdTextField.getText()));
            ArrayList<Ways> waysArrayList = new ArrayList<>();
            String[] text = waysTextField.getText().split(",");
            for (String s : text) {
                for (Ways way : edges) {
                    if (Integer.parseInt(s) == way.getId()) {
                        waysArrayList.add(way);
                    }
                }
            }
            node.setWays(waysArrayList);
            Hashtable<String, String> ht = new Hashtable<>();
            ht.put(tagTextField.getText(), valueTextField.getText());
            node.setOsmNode(ht);
            for (String s : text) {
                for (Integer x : bst.keys()) {
                    Nodes nodes = bst.get(x);
                    for (PoI poi : nodes.getPoI()) {
                        if (poi.getId().equals(Integer.parseInt(s))) {
                            node.setPoI(poi);
                        }
                    }
                }
            }
            Coordinate coordinate = new Coordinate(Double.parseDouble(coordinateXTextField.getText()), Double.parseDouble(coordinateYTextField.getText()));
            node.setPoint(coordinate);
            tableViewNodes.getItems().clear();
            afterReadFileAction();
        } catch (Exception e) {
            System.out.println("Node doesn't exist");
        }
    }

    private void createGraphGroup(int vNumber) {
        createGraph();
        for (int i = 0; i < vNumber; i++) {
            Random r = new Random();
            int radius = 20;

            double posX = radius + r.nextDouble() * 800;
            double posY = radius + r.nextDouble() * 300;

            Circle c = new Circle(posX, posY, radius);
            c.setFill(Color.CORNFLOWERBLUE);
            c.setId("" + i);

            Text text = new Text("" + i);
            StackPane stack = new StackPane();
            stack.setLayoutX(posX - radius);
            stack.setLayoutY(posY - radius);
            stack.getChildren().addAll(c, text);
            graphGroup.getChildren().add(stack);
            //graphGroup.getChildren().add(c);
        }
    }

    public void graphBtn(ActionEvent actionEvent) {
        createGraphGroup(getBstSize() + 1);

        for (Ways ways : edges) {
            DirectedEdge edge = new DirectedEdge(ways.getV(), ways.getW(), ways.getWeight());
            StackPane spv = (StackPane) graphGroup.getChildren().get(ways.getV());
            Circle cv = (Circle) spv.getChildren().get(0);
            ewg.addEdge(edge);
            StackPane spw = (StackPane) graphGroup.getChildren().get(ways.getW());
            Circle cw = (Circle) spw.getChildren().get(0);
            Line line = new Line(cv.getCenterX(), cv.getCenterY(), cw.getCenterX(), cw.getCenterY());
            graphGroup.getChildren().add(line);

        }
        System.out.println(ewg);

    }

    public ArrayList<Nodes> searchTagNodes(String tag) {
        ArrayList<Nodes> nodes = new ArrayList<>();
        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            Enumeration<String> e = node.osmNode.keys();
            while (e.hasMoreElements()) {
                if (Objects.equals(e.nextElement(), tag)) {
                    nodes.add(node);
                }
            }
        }
        return nodes;
    }

    public ArrayList<Ways> searchTagWays(String tag) {
        ArrayList<Ways> ways = new ArrayList<>();
        for (Ways way : edges) {
            Enumeration<String> e = way.osmWay.keys();
            while (e.hasMoreElements()) {
                if (Objects.equals(e.nextElement(), tag)) {
                    ways.add(way);
                }
            }
        }
        return ways;
    }

    public ArrayList<Nodes> searchTagNearCoordinate(Coordinate coordinate) {
        ArrayList<Nodes> nodes = new ArrayList<>();
        double distance;
        for (Integer i : bst.keys()) {
            Nodes node = bst.get(i);
            distance = node.getPoint().calculateDistanceBetweenPoints(node.getPoint().getX(), node.getPoint().getY(), coordinate.getX(), coordinate.getY());

        }
        return nodes;
    }

    public void maxFlowFrom2Nodes() {
        int s = 0, t = bstSize - 1;
        FlowNetwork G = new FlowNetwork(bstSize, edgesSize);

        FordFulkerson maxflow = new FordFulkerson(G, s, t);
        StdOut.println("Max flow from " + s + " to " + t);
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0) {
                    StdOut.println("   " + e);
                }
            }
        }

        StdOut.print("Min cut: ");
        for (int v = 0; v < G.V(); v++) {
            if (maxflow.inCut(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        StdOut.println("Max flow value =" + maxflow.value());
    }

    public void shortestPath(ActionEvent actionEvent) {

        int day = shortDate.getValue().getDayOfMonth();
        int month = shortDate.getValue().getMonthValue();
        int year = shortDate.getValue().getYear();
        Date d = new Date(day, month, year);
        overPopulatedNode(d);
        int from = Integer.parseInt(fromID.getText());
        int to = Integer.parseInt(toID.getText());
        shortestPathNotOverpopulated(from, to);
        if (sp.hasPathTo(to)) {
            System.out.println(sp.pathTo(to));
            System.out.println("Shortest non overpopulated path from vertex " + from + " to vertex " + to + " is " + sp.distTo(to));
        } else {
            System.out.println("There's no such path or there's no non overpopulated route.");
        }

        shortestPath(from, to);
        if (sp.hasPathTo(to)) {
            System.out.println(sp.pathTo(to));
            System.out.println("Shortest path from vertex " + from + " to vertex " + to + " is " + sp.distTo(to));
        } else {
            System.out.println("There's no such path.");
        }
    }

    /**
     * R16
     */
    public void newTagValue() {

    }


    public void addWayBtn(ActionEvent actionEvent) throws IOException {
        Integer id = Integer.parseInt(wayIdTextField.getText());
        String name = wayNameTextField.getText();
        int v = Integer.parseInt(wayNodeVTextField.getText());
        int w = Integer.parseInt(wayNodeWTextField.getText());
        double weight = Double.parseDouble(wayWeightTextField.getText());
        String address = addressTextField.getText();
        String postcode = PostCodeTextField.getText();
        String tag = tagWayTextField.getText();
        String value = valueTextField.getText();
        for (Ways way : edges) {
            if (id.equals(way.getId())) {
                throw new IOException("Node already exists");
            }
        }
        Hashtable<String, String> ht = new Hashtable<>();
        ht.put(tag, value);

        Ways way = new Ways(v, w, weight, id, ht, name, address, postcode);
        edges.add(way);
        tableViewNodes1.getItems().add(way);

    }

    public void editWayBtn(ActionEvent actionEvent) {
        try {
            Integer id = Integer.parseInt(wayIdTextField.getText());
            String name = wayNameTextField.getText();
            int v = Integer.parseInt(wayNodeVTextField.getText());
            int w = Integer.parseInt(wayNodeWTextField.getText());
            double weight = Double.parseDouble(wayWeightTextField.getText());
            String address = addressTextField.getText();
            String postcode = PostCodeTextField.getText();
            String tag = tagWayTextField.getText();
            String value = valueTextField.getText();
            Hashtable<String, String> ht = new Hashtable<>();
            ht.put(tag, value);
            for (Ways way : edges) {
                if (Objects.equals(way.getId(), id)) {
                    way.setOsmWay(ht);
                    way.setAddress(address);
                    way.setName(name);
                    way.setPostcode(postcode);
                    tableViewNodes1.getItems().clear();
                    afterReadFileAction();
                }
            }

        } catch (Exception e) {
            System.out.println("Way doesn't exist");
        }
    }

    public void removeWayBtn(ActionEvent actionEvent) {
        Integer id = tableViewNodes1.getSelectionModel().getSelectedItem().getId();
        edges.removeIf(way -> Objects.equals(way.getId(), id));
        tableViewNodes1.getItems().clear();
        afterReadFileAction();
    }

    public void searchWayBtn(ActionEvent actionEvent) {
        Integer id = Integer.parseInt(searchFieldWay.getText());
        try {
            for (Ways way : edges) {
                if (Objects.equals(way.getId(), id)) {
                    Ways ways = edges.get(edges.indexOf(way));
                    tableViewNodes1.getItems().clear();
                    tableViewNodes1.getItems().add(ways);
                }
            }
        } catch (Exception e) {
            System.out.println("There's no such Way");
        }
    }
}
