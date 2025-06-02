package One_big_Package;

public class CreateAdjacencyMatrix extends MenuItem{
    public CreateAdjacencyMatrix(String name) {
        super(name);
    }

    @Override
    public void execute(Graph graph) {
        AdjacencyMatrixVisitor adjacencyMatrixVisitor = new AdjacencyMatrixVisitor();
        adjacencyMatrixVisitor.visit(graph);
    }
}
