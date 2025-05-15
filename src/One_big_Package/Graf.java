package One_big_Package;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graf implements Collection, Structure{
    private List<Node> listNode = new ArrayList<>();
    private List<Edge> listEdge = new ArrayList<>();

    //Метод для ручного ввода полей узла и выбора конкретной фабрики.
    public void addNode(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите id узла: ");
        String id = in.next();
        System.out.println("Введите описание узла: ");
        String description = in.next();

        NodeFactory nodeFactory = createNodeByNumber();
        Node node = nodeFactory.createNode(id,description);

        this.listNode.add(node);
    }

    // Метод для создание графа по умолчанию.
    public void addNode(String id, String description){
        NodeFactory nodeFactory = createNodeByNumber();
        Node node = nodeFactory.createNode(id,description);

        this.listNode.add(node);
    }

    // Метод для выбора подходящей фабрики узлов.
    static NodeFactoryOne createNodeByNumber(){
            return new NodeFactoryOne();
    }

    // Метод для создания связей между узлами графа вручную (без паттернов)
    public void addEgede(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите узел источнимк: ");
        String sourse = in.next();
        System.out.println("Введите узел приёмник: ");
        String receiver = in.next();
        Edge edge = new Edge(sourse, receiver);

        this.listEdge.add(edge);
    }

    // Метод для создания связей в графе по умолчанию, с шаблонными связями.
    public void addEgede(String sourse, String receiver){
        Edge edge = new Edge(sourse, receiver);
        this.listEdge.add(edge);
    }

    /*public void printAdjacencyMatrix(){
        for (Node value : this.listNode){
            System.out.println(value.getId() + " : " + value.getDescription());
        }
        for (Egede value : this.listEgede){
            System.out.println(value.getId() + " : " + value.getSorse() + " : " + value.getReceiver());
        }
    }*/

    @Override
    public Iterator getIterator(String key) {
        if (key.equalsIgnoreCase("Node")){
            return new NodeIterator();
        } else if (key.equalsIgnoreCase("Egede")){
            return new EgedeIterator();
        }
        return null;
    }

    @Override
    public void beBuild(Visitor visitor) {
        visitor.create(this);
    }

    private class NodeIterator implements Iterator{
        int index = 0;

        @Override
        public boolean hasNext() {
            if (index < listNode.size()){ // переписать под список
                return true;
            }
            return false;
        }

        public Node next() {
            return listNode.get(index++);
        }
    }

    private class EgedeIterator implements Iterator{
        int index = 0;

        @Override
        public boolean hasNext() {
            if (index < listEdge.size()){ // переписать под список
                return true;
            }
            return false;
        }

        public Edge next() {
            return listEdge.get(index++);
        }
    }
}
