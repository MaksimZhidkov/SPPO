package One_big_Package;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class CreateEdge extends MenuItem{
    public CreateEdge(String name) {
        super(name);
    }

    @Override
    public void execute(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя источника: ");
        String sourceName = scanner.nextLine();

        // Проверка на существование такого узла.
        // В начале хотел добавить вариант создать не существующий узел,
        // но не добавил этот функционал.
        Iterator<Node> iteratorSource = graph.nodeIterator();
        Node sourceNode = null;
        while (iteratorSource.hasNext()){
            Node valueNode = iteratorSource.next();
            if(Objects.equals(sourceName, valueNode.getName())){
                sourceNode = valueNode;
                break;
            }
        }

        System.out.print("Введите имя приемника: ");
        String targetName= scanner.nextLine();

        //Аналогичная проверка на наличие узла.
        Iterator<Node> iteratorTarget = graph.nodeIterator();
        Node targetNode = null;
        while (iteratorTarget.hasNext()){
            Node valueNode = iteratorTarget.next();
            if(Objects.equals(targetName, valueNode.getName())){ // Как сделать по-другому?
                targetNode = valueNode;
                break;
            }
        }

        System.out.print("Введите описание отношения: ");
        String relation= scanner.nextLine();


        Edge edge = new Edge(sourceNode, targetNode, relation);
        graph.addEgede(edge);
    }
}
