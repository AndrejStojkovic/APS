// Lab 10.1

// Your task is to create an unoriented unweighted graph by using an adjacency list,
// where each vertex information is аn integer. You create the graph according to the received commands.
// You will be given an array of commands that can be one of the following:

// CREATE - you should create a new graph.
// ADDEDGE [number1] [number2] - you should create an edge between the vertices with ordinal number number1 and ordinal number number2. 
// DELETEEDGE [number1] [number2] - you should remove the edge between the vertices with ordinal number number1 and ordinal number number2.
// ADЈACENT [number1] [number2] - you should print true if the vertices with ordinal number number1 and ordinal number number2 are adjacent, otherwise print false.
// PRINTGRAPH - you should print the adjacency list. 

import java.util.*;

class Graph<E> {
    private Map<E, Set<E>> adjacencyList;

    Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(E vertex) {
        if(!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    public void removeVertex(E vertex) {
        for(Set<E> neighbours : adjacencyList.values()) {
            neighbours.remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(E x, E y) {
        addVertex(x);
        addVertex(y);
        adjacencyList.get(x).add(y);
        adjacencyList.get(y).add(x);
    }

    public void deleteEdge(E x, E y) {
        if(adjacencyList.containsKey(x))
            adjacencyList.get(x).remove(y);

        if(adjacencyList.containsKey(y))
            adjacencyList.get(y).remove(x);
    }

    public Set<E> getNeighbours(E vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public boolean isAdjacent(E x, E y) {
        return getNeighbours(x).contains(y);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        adjacencyList.forEach((x, y) -> str.append(x.toString()).append(": ").append(new ArrayList<>(y)).append("\n"));
        return str.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Graph<Integer> graph = new Graph<>();
        int n = in.nextInt();
        in.next();

        for(int i = 0; i < n; i++) {
            String [] line = in.nextLine().split(" ");

            switch(line[0]) {
                case "CREATE":
                    graph = new Graph<>();
                case "ADDEDGE":
                    graph.addEdge(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    break;
                case "DELETEEDGE":
                    graph.deleteEdge(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                    break;
                case "ADJACENT":
                    System.out.println(graph.isAdjacent(Integer.parseInt(line[1]), Integer.parseInt(line[2])));
                    break;
                case "PRINTGRAPH":
                    System.out.println(graph);
                    break;
            }
        }
    }
}
