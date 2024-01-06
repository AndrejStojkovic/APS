// Lab 11.1

// On one university there are mandatory and elective subjects. For each subject you are given,
// a list of subjects that need to be passed in order to be eligible for that subject is given.
// Find the next available subject for you, if you know what is the last subject that you have passed.

import java.util.*;

class GraphNodeNeighbor<E> {
    GraphNode<E> node;
    float weight;

    public GraphNodeNeighbor(GraphNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight = 0;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>) obj;
        return pom.node.equals(this.node);
    }
}

class GraphNode<E> {
    private int index;
    private E info;
    private LinkedList<GraphNodeNeighbor<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNodeNeighbor<E>>();
    }

    public boolean containsNeighbor(GraphNode<E> o) {
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o, 0);
        return neighbors.contains(pom);
    }

    public void addNeighbor(GraphNode<E> o, float weight) {
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o, weight);
        neighbors.add(pom);
    }

    public void removeNeighbor(GraphNode<E> o) {
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o, 0);
        if (neighbors.contains(pom))
            neighbors.remove(pom);
    }

    @Override
    public String toString() {
        String ret = "INFO:" + info + " SOSEDI:";
        for (int i = 0; i < neighbors.size(); i++)
            ret += "(" + neighbors.get(i).node.info + ","
                    + neighbors.get(i).weight + ") ";
        return ret;

    }

    public void updateNeighborWeight(GraphNode<E> o, float weight) {
        Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
        while (i.hasNext()) {
            GraphNodeNeighbor<E> pom = i.next();
            if (pom.node.equals(o))
                pom.weight = weight;
        }

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>) obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
        this.neighbors = neighbors;
    }

}

class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y, float tezina) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        if (adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y], tezina);
        } else
            adjList[x].addNeighbor(adjList[y], tezina);
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    // Funkcija za prebaruvanje na index na jazel so dadeno info vo listata na
    // sosednost vo grafot
    int searchIndex(String key) {
        for (int i = 0; i < num_nodes; i++) {
            Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors()
                    .iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                if (pom.node.getInfo().equals(key))
                    return pom.node.getIndex();

            }
        }
        return -1;
    }

    E searchKey(int index) {
        for (int i = 0; i < num_nodes; i++) {
            Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors()
                    .iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                if (pom.node.getIndex() == (index))
                    return pom.node.getInfo();

            }
        }
        return null;
    }

    float[] dijkstra(int from, int to) {
        int start=from;
        float distance[] = new float[this.num_nodes];
        boolean finalno[] = new boolean[this.num_nodes];
        int niza[]=new int[num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            distance[i] = -1;
            finalno[i] = false;
            niza[i]=i;
        }

        finalno[from] = true;
        distance[from] = 0;
        for (int i = 1; i < this.num_nodes; i++) {
            Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors().iterator();
            while (it.hasNext()) {

                GraphNodeNeighbor<E> pom = it.next();
                if (!finalno[pom.node.getIndex()]) {
                    if (distance[pom.node.getIndex()] <= 0) {
                        distance[pom.node.getIndex()] = distance[from] + pom.weight;
                        niza[pom.node.getIndex()]=from;
                    }
                    else if (distance[from] + pom.weight < distance[pom.node
                            .getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
                        niza[pom.node.getIndex()]=from;
                    }
                }
            }
            boolean minit = false;
            int k = -1;
            float minc = -1;
            for (int j = 0; j < this.num_nodes; j++) {
                if (!finalno[j] && distance[j] != -1) {
                    if (!minit) {
                        minc = distance[k = j];
                        minit = true;
                    }

                    else if (minc > distance[j] && distance[j] > 0)
                        minc = distance[k = j];
                }
            }
            finalno[from = k] = true;

        }

        int n=to;
        Stack<E> stack=new Stack<E>();
        stack.push(searchKey(n));
        while(true){
            n=niza[n];
            stack.push(searchKey(n));
            if (n==start) break;
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        System.out.println();

        return distance;

    }

    float min_path(int from, int to) {
        float [] a=dijkstra(from, to);
        float [] b=dijkstra(to, from);
        return a[to] + b[from];
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();

        Graph<String> graph = new Graph<>(n, new String[n]);

        for(int i = 0; i < m; i++) {
            String input = in.nextLine();
            String [] line = input.split(" ");
            int x = Integer.parseInt(line[0]), y = Integer.parseInt(line[2]);
            float weight = Float.parseFloat(line[4]);
            graph.adjList[x].setInfo(line[1]);
            graph.adjList[y].setInfo(line[3]);
            graph.addEdge(x, y, weight);
        }

        int startIdx = graph.searchIndex(in.nextLine());
        int endIdx = graph.searchIndex(in.nextLine());
        System.out.println(graph.min_path(startIdx, endIdx));
    }
}
