package One_big_Package;

public class Exit extends MenuItem{
    public Exit(String name) {
        super(name);
    }

    @Override
    public void execute(Graph graph) {
        System.exit(0);
    }
}
