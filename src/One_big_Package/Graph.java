package One_big_Package;

import java.util.*;

public class Graph {
    private List<Node> listNode = new ArrayList<>();
    private List<Edge> listEdge = new ArrayList<>();
    private int[][] adjacencymatrix;
    private int[][] identityMatrix;
    private static Graph graph;


    // Создаю элемент фасада (одиночку)
    private Graph(){
    }
    public static synchronized Graph getGraph(){ // добавить synchronized для многопоточки потом.
        if (graph == null) {
            graph = new Graph();
        }
        return graph;
    }


    // Методы для добавления узлов и связей в поля графа
    public void addNode(Node node){
        this.listNode.add(node);
    }
    public void addEgede(Edge edge){ this.listEdge.add(edge);}

    // Возвращаю списки узлов и связей
    public List<Node> getNodes() {
        return listNode;
    }
    public List<Edge> getEdges(Edge edge) {
        return listEdge;
    }

    // Возвращаю размеры списков узлов и связей
    public int getNodeListSize(){ return listNode.size();}
    public int getEdgeListSize(){ return listEdge.size();}

    // Итераторы по узлам и связям
    public Iterator<Node> nodeIterator() {
        return new GraphIterator<>(listNode.iterator());
    }
    public Iterator<Edge> edgeIterator() {
        return new GraphIterator<>(listEdge.iterator());
    }

    public void createAdjacencyMatrix() {
        Iterator<Node> nodeIterator = graph.nodeIterator();
        int size = graph.getNodeListSize();
        adjacencymatrix = new int[size][size];

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            indexMap.put(nodeIterator.next().getName(), i);
        }

// Заполняем матрицу смежности: 1 если есть связь из i в j
        Iterator<Edge> edgeIterator = graph.edgeIterator();
        while (edgeIterator.hasNext()) {
            Edge edge = edgeIterator.next();
            int i = indexMap.get(edge.getSource());
            int j = indexMap.get(edge.getTarget());
            adjacencymatrix[i][j] = 1;
            adjacencymatrix[j][i] = 1;
        }
    }

    public void createIdentityMatrix(){
        Iterator<Node> nodeIterator = graph.nodeIterator();
        int sizeNode = graph.getNodeListSize();
        int sizeEdge = graph.getEdgeListSize();
        identityMatrix = new int[sizeEdge][sizeNode];

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i=0; i<sizeNode; i++) {
            indexMap.put(nodeIterator.next().getName(), i);
        }

// Заполняем матрицу смежности: 1 если есть связь из i в j
        Iterator<Edge> edgeIterator = graph.edgeIterator();
        int counter = 0;
        while (edgeIterator.hasNext()){
            Edge edge = edgeIterator.next();
            int i = indexMap.get(edge.getSource());
            int j = indexMap.get(edge.getTarget());
            identityMatrix[counter][i] = 1;
            identityMatrix[counter][j] = 1; // если сделать ориентированный граф, то тут нужно поставить -1.
            counter++;
        }
    }

    public void printMatrix(){
        Iterator<Node> nodeIterator = graph.nodeIterator();
        int size = graph.getNodeListSize();
        System.out.println("Матрица смежности:");
        System.out.print("     ");
        while (nodeIterator.hasNext()) {
            System.out.printf("%5s", nodeIterator.next().getName());
        }
        System.out.println();

        nodeIterator = graph.nodeIterator();
        for (int i=0; i<size; i++) {
            System.out.printf("%5s", nodeIterator.next().getName());
            for (int j=0; j<size; j++) {
                System.out.printf("%5d", adjacencymatrix[i][j]);
            }
            System.out.println();
        }

        nodeIterator = graph.nodeIterator();
        System.out.println("Матрица инцидентности:");
        System.out.print("     ");
        while (nodeIterator.hasNext()) {
            System.out.printf("%5s", nodeIterator.next().getName());
        }
        System.out.println();

        int sizeNode = graph.getNodeListSize();
        int sizeEdge = graph.getEdgeListSize();
        Iterator<Edge> edgeIterator = graph.edgeIterator();
        for (int i=0; i<sizeEdge; i++) {
            System.out.printf("%5s", edgeIterator.next().getName());
            for (int j=0; j<sizeNode; j++) {
                System.out.printf("%5d", identityMatrix[i][j]);
            }
            System.out.println();
        }
    }

}
