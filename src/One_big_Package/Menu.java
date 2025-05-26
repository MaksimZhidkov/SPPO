package One_big_Package;

import java.util.*;

// Класс для меню. Реализует паттерн Компоновщика.

public class Menu implements IMenuComponent {
    private final List<IMenuComponent> components = new ArrayList<>();
    private final String name;

    public Menu(String name) {
        this.name = name;
    }

    // Метод для добавления компонентов (блюд или подменю) в меню
    @Override
    public void add(IMenuComponent component) {
        components.add(component);
    }

    // Метод для удаления компонентов (блюд или подменю) из меню
    @Override
    public void remove(IMenuComponent component) {
        components.remove(component);
    }

    // Метод для отображения содержимого меню с отступом
    @Override
    public void display(int depth) {
        System.out.println("-".repeat(depth) + name);
        for (IMenuComponent component : components) {
            component.display(depth + 2);
        }
    }

// Метод для выбора действий совершаемых над графом.
    public int functionMenu(Graph graph,String key){
        switch (key){
            case "0":
                return createDefaultGraph(graph);
            case "1":
                return createNode(graph);
            case "2":
                return createEdge(graph);
            case "3":
                return createAdjacencyMatrix(graph);
            case "4":
                return createIdentetyMatrix(graph);
            case "5":
                return 0;
            default:
                return 0;
        }
    }

    // Вывод графа по умолчанию.
    private int createDefaultGraph(Graph graph) {
        // Ввожу узлы.
        Node nodeA = new Node("A", "First");
        graph.addNode(nodeA);
        Node nodeB = new Node("B", "Second");
        graph.addNode(nodeB);
        Node nodeC = new Node("C", "Third");
        graph.addNode(nodeC);
        Node nodeD = new Node("D", "Fourth");
        graph.addNode(nodeD);

        // Создаю связи.
        Edge edgeA = new Edge(nodeA, nodeB, "First-Second");
        graph.addEgede(edgeA);
        Edge edgeB = new Edge(nodeA, nodeC, "First-Third");
        graph.addEgede(edgeB);
        Edge edgeC = new Edge(nodeA, nodeD, "First-Fifth");
        graph.addEgede(edgeC);
        Edge edgeD = new Edge(nodeB, nodeD, "Second-Fifth");
        graph.addEgede(edgeD);
        Edge edgeE = new Edge(nodeC, nodeB, "Third-Second");
        graph.addEgede(edgeE);
        // Вывожу матрицы
        createAdjacencyMatrix(graph);
        createIdentetyMatrix(graph);
        return 1;
    }

    // Методы для добавления узлов и связей.
    public int createNode(Graph graph){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя узла: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание: ");
        String desc = scanner.nextLine();

    // Реализация фабрики узлов.
        Node node = graph.createNodeFactory().createConcreteNode(name, desc);
        graph.addNode(node);
        return 1;
    }
    public int createEdge(Graph graph){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя источника: ");
        String sourceName = scanner.nextLine();

    // Проверка на существование такого узла.
    // В начале хотел добавить вариант создать не существующий узел,
    // но не добавил этот функционал.
        Iterator<Node> iteratorSource = graph.nodeIterator();
        Node sourceNode = null;
        while (iteratorSource.hasNext()){
            Node valueNode = iteratorSource.next();
            if(Objects.equals(sourceName, valueNode.getName())){
                sourceNode = valueNode;
                break;
            }
        }

        System.out.print("Введите имя приемника: ");
        String targetName= scanner.nextLine();

    //Аналогичная проверка на наличие узла.
        Iterator<Node> iteratorTarget = graph.nodeIterator();
        Node targetNode = null;
        while (iteratorTarget.hasNext()){
            Node valueNode = iteratorTarget.next();
            if(Objects.equals(targetName, valueNode.getName())){ // Как сделать по-другому?
                targetNode = valueNode;
                break;
            }
        }

        System.out.print("Введите описание отношения: ");
        String relation= scanner.nextLine();


        Edge edge = new Edge(sourceNode, targetNode, relation);
        graph.addEgede(edge);
        return 1;
    }

    // Методы для построения матриц.
    private int createAdjacencyMatrix(Graph graph) {
        AdjacencyMatrixVisitor adjacencyMatrixVisitor = new AdjacencyMatrixVisitor();
        adjacencyMatrixVisitor.visit(graph);
        return 1;
    }
    private int createIdentetyMatrix(Graph graph) {
        IdentityMatrix identityMatrix = new IdentityMatrix();
        identityMatrix.visit(graph);
        return 1;
    }
}
