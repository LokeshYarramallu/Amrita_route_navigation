import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




interface ShortestPathAlgorithm {                                                                                                               // Interface and abstrcation concept
    void addEdge(int source, int destination, int weight);

    List<Integer> findShortestPath(int start, int end);
}

class DijkstraAlgorithm implements ShortestPathAlgorithm {
    private int numNodes;
    private Map<Integer, List<Edge>> graph;                                                                                                    // Array (or) List (or) HashMap Concept

    DijkstraAlgorithm(){
        System.out.println("You need to pass the number of Nodes as a parameter to create an object for the class...!");
    }

    DijkstraAlgorithm(int numNodes) {                                                                                                          // Contruction overloading ( CompileTime Polymorphism )
        this.numNodes = numNodes;
        this.graph = new HashMap<>();
        for (int i = 0; i < numNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
    }

    @Override                                                                                                                                   // Method Overriding ( RunTime polymorphism )
    public void addEdge(int source, int destination, int weight) {
        graph.get(source).add(new Edge(destination, weight));
        graph.get(destination).add(new Edge(source, weight));
    }

    public List<Integer> findShortestPath(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        int[] distances = new int[numNodes];
        int[] prev = new int[numNodes];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        distances[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.node == end) {
                break;
            }

            if (curr.distance > distances[curr.node]) {
                continue;
            }

            for (Edge edge : graph.get(curr.node)) {
                int newDistance = distances[curr.node] + edge.weight;
                if (newDistance < distances[edge.destination]) {
                    distances[edge.destination] = newDistance;
                    prev[edge.destination] = curr.node;
                    pq.add(new Node(edge.destination, newDistance));
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int currNode = end;
        while (currNode != -1) {
            path.add(currNode);
            currNode = prev[currNode];
        }
        Collections.reverse(path);
        return path;
    }

    private static class Node {
        int node;
        int distance;

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}

class LandmarkMapper {
    private Map<String, Integer> landmarkMap;

    public LandmarkMapper() {
        landmarkMap = new HashMap<>();
        landmarkMap.put("Ashram main gate", 1);
        landmarkMap.put("Vrindavan Hostel", 2);
        landmarkMap.put("Swimming pool", 3);
        landmarkMap.put("Bhajan Hall", 4);
        landmarkMap.put("Prahallada Hostel", 5);
        landmarkMap.put("Dhanalakshmi Bank ATM", 6);
        landmarkMap.put("Beach", 7);
        landmarkMap.put("Amrithsethu", 8);
        landmarkMap.put("BioTech Block", 9);
        landmarkMap.put("BioTech Canteen", 10);
        landmarkMap.put("WNA Building", 11);
        landmarkMap.put("Business Block", 12);
        landmarkMap.put("Parking Lot", 13);
        landmarkMap.put("College Main Gate", 14);
        landmarkMap.put("DTDC courier office", 15);
        landmarkMap.put("College ATM", 16);
        landmarkMap.put("Amriteswari Hall", 17);
        landmarkMap.put("Acharya Hall", 18);
        landmarkMap.put("Inside Central Lobby (Stairs)", 19);
        landmarkMap.put("Lover's Park (Lil Canteen)", 20);
        landmarkMap.put("Facaulty Mess", 21);
        landmarkMap.put("Girls Hostel gate", 22);
        landmarkMap.put("Amritham Restaurent", 23);
        landmarkMap.put("Senior Boys Hostel", 24);
        landmarkMap.put("Bus Ground (Campus Hospital)", 25);
        landmarkMap.put("Ground", 26);
        landmarkMap.put("Andhra Ruchulu", 27);
        landmarkMap.put("Casablanca", 28);
        landmarkMap.put("Library", 29);
        landmarkMap.put("New Mess Hall", 30);
        landmarkMap.put("Canteen", 31);
        landmarkMap.put("Online Delivery Parcel office", 32);
        landmarkMap.put("Girls Hostel", 33);
        landmarkMap.put("Lobby (Amma Statue)", 34);
    }

    public int getLandmarkNumber(String landmarkName) {
        Integer number = landmarkMap.get(landmarkName);
        return (number != null) ? number : 0; // Returning 0 for unknown landmarks.
    }

    public String getLandmarkName(int num) {
        for (Map.Entry<String, Integer> entry : landmarkMap.entrySet()) {
            if (entry.getValue() == num) {
                return entry.getKey();
            }
        }
        return "Landmark number not found!";
    }
}

class DijkstraShortestPath {

    public static String path(String a, String b) {

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(36);
        dijkstra.addEdge(1, 7, 140);
        dijkstra.addEdge(1, 2, 40);
        dijkstra.addEdge(1, 4, 65);
        dijkstra.addEdge(1, 3, 25);
        dijkstra.addEdge(1, 6, 50);
        dijkstra.addEdge(2, 4, 75);
        dijkstra.addEdge(4, 5, 100);
        dijkstra.addEdge(1, 8, 95);
        dijkstra.addEdge(8, 9, 230);
        dijkstra.addEdge(9, 11, 35);
        dijkstra.addEdge(11, 10, 10);
        dijkstra.addEdge(9, 12, 65);
        dijkstra.addEdge(12, 33, 165);
        dijkstra.addEdge(12, 13, 75);
        dijkstra.addEdge(13, 14, 10);
        dijkstra.addEdge(13, 16, 30);
        dijkstra.addEdge(14, 16, 35);
        dijkstra.addEdge(16, 22, 95);
        dijkstra.addEdge(22, 33, 100);
        dijkstra.addEdge(22, 23, 65);
        dijkstra.addEdge(23, 26, 140);
        dijkstra.addEdge(14, 25, 200);
        dijkstra.addEdge(25, 24, 160);
        dijkstra.addEdge(14, 30, 60);
        dijkstra.addEdge(14, 15, 50);
        dijkstra.addEdge(15, 27, 50);
        dijkstra.addEdge(27, 28, 70);
        dijkstra.addEdge(8, 28, 215);
        dijkstra.addEdge(14, 34, 40);
        dijkstra.addEdge(34, 19, 5);
        dijkstra.addEdge(19, 17, 10);
        dijkstra.addEdge(19, 18, 10);
        dijkstra.addEdge(19, 32, 70);
        dijkstra.addEdge(19, 29, 40);
        dijkstra.addEdge(34, 20, 40);
        dijkstra.addEdge(20, 29, 30);
        dijkstra.addEdge(29, 31, 40);

        LandmarkMapper IDs = new LandmarkMapper();
        int startNode = IDs.getLandmarkNumber(a);
        int endNode = IDs.getLandmarkNumber(b);
        List<Integer> shortestPath = dijkstra.findShortestPath(startNode, endNode);

        String ans = "Your Route is   :    ";

        for (int node : shortestPath) {
            ans += IDs.getLandmarkName(node) + "    --->  ";
            System.out.println(IDs.getLandmarkName(node));
        }
        return ans;
    }
}

public class GUI extends JFrame {                                                                                                                               // Inheritance                                                                      
    private JComboBox<String> dropdown;                                                                                                                         // JavaSwing  (GUI concept)
    private JComboBox<String> dropdown2;
    private JButton submitButton;
    private JLabel responseLabel;

    public GUI() {
        super();
        DijkstraShortestPath ROUTE =  new DijkstraShortestPath();
        setTitle("Swing GUI Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        String[] options = { "Select the Location","Ashram main gate",
            "Vrindavan Hostel",
            "Swimming pool",
            "Bhajan Hall",
            "Prahallada Hostel",
            "Dhanalakshmi Bank ATM",
            "Beach",
            "Amrithsethu",
            "BioTech Block",
            "BioTech Canteen",
            "WNA Building",
            "Business Block",
            "Parking Lot",
            "College Main Gate",
            "DTDC courier office",
            "College ATM",
            "Amriteswari Hall",
            "Acharya Hall",
            "Inside Central Lobby (Stairs)",
            "Lover's Park (Lil Canteen)",
            "Faculty Mess",
            "Girls Hostel gate",
            "Amritham Restaurant",
            "Senior Boys Hostel",
            "Bus Ground (Campus Hospital)",
            "Ground",
            "Andhra Ruchulu",
            "Casablanca",
            "Library",
            "New Mess Hall",
            "Canteen",
            "Online Delivery Parcel office",
            "Girls Hostel",
            "Lobby (Amma Statue)" };

        dropdown = new JComboBox<>(options);
        dropdown2 = new JComboBox<>(options);

        submitButton = new JButton("Give the Route");
        Dimension buttonSize = new Dimension(150, 30); 
        submitButton.setPreferredSize(buttonSize);
        submitButton.setMinimumSize(buttonSize);
        submitButton.setMaximumSize(buttonSize);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) dropdown.getSelectedItem();
                String selectedOption2 = (String) dropdown2.getSelectedItem();


                responseLabel.setText(ROUTE.path(selectedOption, selectedOption2));
                responseLabel.setForeground(Color.MAGENTA);
                responseLabel.setBackground(getForeground());
            }
        });

        responseLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(dropdown);
        panel.add(dropdown2);
        panel.add(submitButton);

        add(panel, BorderLayout.NORTH);
        add(responseLabel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        try{                                                                                                                                // Execption handling
            GUI swingGUI = new GUI();
        }
        catch(Exception e){
            System.out.println("Object to the above class connot be created as there is a issue of "+e);
        }
        finally{
            System.out.println("This is the map navigation of Amrita Amrithpuri..!");
        }
    }
}