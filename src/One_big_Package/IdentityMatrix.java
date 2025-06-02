package One_big_Package;

import java.util.*;

// Класс матрицы, реализует посетитель.

public class IdentityMatrix implements GraphVisitor{

    @Override
    public void visit(Graph graph) {
// Получаем список узлов для индексации по порядку
        Iterator<Node> nodeIterator = graph.nodeIterator();
        int sizeNode = graph.getNodeListSize();
        int sizeEdge = graph.getEdgeListSize();
        int[][] matrix = new int[sizeEdge][sizeNode];

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i=0; i<sizeNode; i++) {
            indexMap.put(nodeIterator.next().getName(), i);
        }

// Заполняем матрицу смежности: 1 если есть связь из i в j
        Iterator<Edge> edgeIterator = graph.edgeIterator();
        int counter = 0;
        while (edgeIterator.hasNext()){
            Edge edge = edgeIterator.next();
            int i = indexMap.get(edge.getSource().getName());
            int j = indexMap.get(edge.getTarget().getName());
            matrix[counter][i] = 1;
            matrix[counter][j] = 1; // если сделать ориентированный граф, то тут нужно поставить -1.
            counter++;
        }

// Выводим таблицу
        nodeIterator = graph.nodeIterator();
        System.out.println("Матрица инцидентности:");
        System.out.print("     ");
        while (nodeIterator.hasNext()) {
            System.out.printf("%5s", nodeIterator.next().getName());
        }
        System.out.println();

        edgeIterator = graph.edgeIterator();
        for (int i=0; i<sizeEdge; i++) {
            System.out.printf("%5s", edgeIterator.next().getName());
            for (int j=0; j<sizeNode; j++) {
                System.out.printf("%5d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
