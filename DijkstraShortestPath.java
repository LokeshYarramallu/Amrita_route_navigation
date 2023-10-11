import java.util.*;

interface ShortestPathAlgorithm {                                                                                                                                // Interface and Abstraction concept
    void addEdge(int source, int destination, int weight);
    List<Integer> findShortestPath(int start, int end);
}

class DijkstraAlgorithm implements ShortestPathAlgorithm {

    private int numNodes;
    private Map<Integer, List<Edge>> graph;

    DijkstraAlgorithm(){
        System.out.println("Number of Nodes have to be passed as a parameter..!");
    }


    DijkstraAlgorithm(int numNodes) {                                                                                                                          // Constructor Overloading
        this.numNodes = numNodes;
        this.graph = new HashMap<>();
        for (int i = 0; i < numNodes; i++) {
            graph.put(i, new ArrayList<>());
        }
    }


    @Override                                                                                                                                                  // RunTime Polymorphism (Overloading)
    public void addEdge(int source, int destination, int weight) {
        graph.get(source).add(new Edge(destination, weight));
        graph.get(destination).add(new Edge(source, weight));
    }

    public List<Integer> findShortestPath(int start, int end) {                                                                                                // Array (or) List (or) HashMap concept
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

class InstructionsForUser {

    int NoOFLocations = 34;

    InstructionsForUser() {

        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        System.out.println("WELCOME TO ROUTE MAP NAVIGATION IN AMRITA VISHWA VIDYAAPEETHAM, AMRITPURI !");
        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void LocationTable() {
        GetLandmarkName getLandmarkNames = new GetLandmarkName();
        Object[][] table = new Object[NoOFLocations][2];
        for (int i = 0; i < 34; i++) {
            table[i][0] = i + 1;
            table[i][1] = getLandmarkNames.getLandmarkName(i + 1);
        }

        System.out.println("Number\tLandmark");
        for (Object[] row : table) {
            System.out.println(row[0] + "\t" + row[1]);
        }
    }
}

class GetLandmarkName extends InstructionsForUser {                                                                                                            // Inheritance
    public String getLandmarkName(int num) {
        if (NoOFLocations == 34) {
            switch (num) {
                case 1:
                    return "Ashram main gate";
                case 2:
                    return "Vrindavan Hostel";
                case 3:
                    return "Swimming pool";
                case 4:
                    return "Bhajan Hall";
                case 5:
                    return "Prahallada Hostel";
                case 6:
                    return "Dhanalakshmi Bank ATM";
                case 7:
                    return "Beach";
                case 8:
                    return "Amrithsethu";
                case 9:
                    return "BioTech Block";
                case 10:
                    return "BioTech Canteen";
                case 11:
                    return "WNA Building";
                case 12:
                    return "Business Block";
                case 13:
                    return "Parking Lot";
                case 14:
                    return "College Main Gate";
                case 15:
                    return "DTDC courier office";
                case 16:
                    return "College ATM";
                case 17:
                    return "Amriteswari Hall";
                case 18:
                    return "Acharya Hall";
                case 19:
                    return "Inside Central Lobby (Stairs)";
                case 20:
                    return "Lover's Park (Lil Canteen)";
                case 21:
                    return "Facaulty Mess";
                case 22:
                    return "Girls Hostel gate";
                case 23:
                    return "Amritham Restaurent";
                case 24:
                    return "Senior Boys Hostel";
                case 25:
                    return "Bus Ground (Campus Hospital)";
                case 26:
                    return "Ground";
                case 27:
                    return "Andhra Ruchulu";
                case 28:
                    return "Casablanca";
                case 29:
                    return "Library";
                case 30:
                    return "New Mess Hall";
                case 31:
                    return "Canteen";
                case 32:
                    return "Online Delivery Parcel office";
                case 33:
                    return "Girls Hostel";
                case 34:
                    return "Lobby (Amma Statue)";
                default:
                    return "This won't be used anyways :)";
            }
        } else {
            return "Something is wrong ! Number of locations does not match number of variables.";
        }
    }
}

public class DijkstraShortestPath {

    public static void main(String[] args) {

        GetLandmarkName temp = new GetLandmarkName();
        InstructionsForUser LocationsAndNumbers = new InstructionsForUser();
        LocationsAndNumbers.LocationTable();

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

        Scanner scanner = new Scanner(System.in);

        try {                                                                                                                                                   // Threads Concept
            Thread.sleep(1000);                                                                                                                          // Exception Handling
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        System.out.print("Enter the number in the above table, corresponding to your STARTING location: ");
        int startNode = scanner.nextInt();

        while (startNode < 1 || startNode > 34) {
            System.out.print(
                    "The value corresponding to the starting location must be an integer between 1 and 34! Re-enter: ");
            startNode = scanner.nextInt();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the number in the above table, corresponding to your ENDING location: ");
        int endNode = scanner.nextInt();
        scanner.close();

        while (endNode < 1 || endNode > 34) {
            System.out.print(
                    "The value corresponding to the ending location must be an integer between 1 and 34! Re-enter: ");
            endNode = scanner.nextInt();
        }

        List<Integer> shortestPath = dijkstra.findShortestPath(startNode, endNode);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("");

        System.out.println(
                "--------------------------------------------------------------------------------------------------");
        System.out.println(
                "Navigation from " + temp.getLandmarkName(startNode) + " to " + temp.getLandmarkName(endNode) + ":");
        System.out.println(
                "--------------------------------------------------------------------------------------------------");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("You are at -> " + temp.getLandmarkName(startNode));
        for (int node : shortestPath) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("From there, go to " + temp.getLandmarkName(node) + ".");
        }
        System.out.print("That is your Final destination.");
    }
}