package One_big_Package;

import java.util.Scanner;

public class CreateNode extends MenuItem {

    public CreateNode(String name) {
        super(name);
    }

    @Override
    public void execute(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя узла: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание: ");
        String desc = scanner.nextLine();

        // Реализация фабрики узлов.
        Node node = graph.createNodeFactory().createConcreteNode(name, desc);
        graph.addNode(node);
    }
}
