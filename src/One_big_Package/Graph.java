package One_big_Package;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph {
    private List<Node> listNode = new ArrayList<>();
    private List<Edge> listEdge = new ArrayList<>();

    // Методы для добавления узлов и связей в поля графа
    public void addNode(Node node){
        this.listNode.add(node);
    }
    public void addEgede(Edge edge){ this.listEdge.add(edge);}

    //Метод создания конкретной фабрики.
    public NodeFactory createNodeFactory(){
            return new NodeFactory();
    }

    // Возвращаю списки узлов и связей
    public List<Node> getNodes() {
        return listNode;
    }
    public List<Edge> getEdges() {
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
}
