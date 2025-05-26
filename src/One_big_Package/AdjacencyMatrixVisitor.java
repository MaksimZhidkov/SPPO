package One_big_Package;

import java.util.*;

// Класс матрицы, реализует посетителя.

public class AdjacencyMatrixVisitor implements GraphVisitor {
    @Override
    public void visit(Graph graph) {
// Получаем список узлов для индексации по порядку
        Iterator<Node> nodeIterator = graph.nodeIterator();
        int size = graph.getNodeListSize();
        int[][] matrix = new int[size][size];

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i=0; i<size; i++) {
            indexMap.put(nodeIterator.next().getName(), i);
        }

// Заполняем матрицу смежности: 1 если есть связь из i в j
        Iterator<Edge> edgeIterator = graph.edgeIterator();
        while (edgeIterator.hasNext()){
            Edge edge = edgeIterator.next();
            int i = indexMap.get(edge.getSource().getName());
            int j = indexMap.get(edge.getTarget().getName());
            matrix[i][j] = 1;
            matrix[j][i] = 1;
        }


// Выводим таблицу
        nodeIterator = graph.nodeIterator();
        System.out.println("Матрица смежности:");
        System.out.print("     ");
        while (nodeIterator.hasNext()) {
            System.out.printf("%5s", nodeIterator.next().getName());
        }
        System.out.println();

        nodeIterator = graph.nodeIterator();
        for (int i=0; i<size; i++) {
            System.out.printf("%5s", nodeIterator.next().getName());
            for (int j=0; j<size; j++) {
                System.out.printf("%5d", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
