package One_big_Package;

public class CreateDefaultGraph extends MenuItem{
    public CreateDefaultGraph(String name) {
        super(name);
    }

    public void execute(Graph graph){
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
        AdjacencyMatrixVisitor adjacencyMatrixVisitor = new AdjacencyMatrixVisitor();
        adjacencyMatrixVisitor.visit(graph);
        IdentityMatrix identityMatrix = new IdentityMatrix();
        identityMatrix.visit(graph);
    }
}
