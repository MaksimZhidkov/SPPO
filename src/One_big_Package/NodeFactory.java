package One_big_Package;

// фабрика, тк узел всего один, она не интересная.

public class NodeFactory {
    public Node createConcreteNode(String name, String description) {
        return new Node(name, description);
    }
}
