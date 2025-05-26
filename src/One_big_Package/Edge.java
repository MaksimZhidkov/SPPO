package One_big_Package;

// Класс связей.

public class Edge {
    private Node source;
    private Node target;
    private String relation;

    public Edge(Node source, Node target, String relation) {
        this.source = source;
        this.target = target;
        this.relation = relation;
    }

    public Node getSource() {
        return source;
    }
    public Node getTarget() {
        return target;
    }
    public String getRelation() {
        return relation;
    }
    public String getName(){
        return source.getName()+target.getName();
    }
}