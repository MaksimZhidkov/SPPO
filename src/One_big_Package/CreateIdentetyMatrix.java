package One_big_Package;

public class CreateIdentetyMatrix extends MenuItem{
    public CreateIdentetyMatrix(String name) {
        super(name);
    }

    @Override
    public void execute(Graph graph) {
        IdentityMatrix identityMatrix = new IdentityMatrix();
        identityMatrix.visit(graph);
    }
}
