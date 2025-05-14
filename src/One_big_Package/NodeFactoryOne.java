package One_big_Package;

public class NodeFactoryOne implements NodeFactory {
    @Override
    public Node createNode(String id, String description) {
        return new NodeOne(id, description);
    }
}
