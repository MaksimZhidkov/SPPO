package One_big_Package;

// класс узлов

public class Node {
    private String name;
    private String description;

    public Node(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}