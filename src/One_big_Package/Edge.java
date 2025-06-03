package One_big_Package;

// Класс связей.

public class Edge {
    private String source;
    private String target;
    private String relation;

    public Edge(String source, String target, String relation) {
        this.source = source;
        this.target = target;
        this.relation = relation;
    }

    public String getSource() {
        return source;
    }
    public String getTarget() {
        return target;
    }
    public String getRelation() {
        return relation;
    }
    public String getName(){
        return source+target;
    }
}