package One_big_Package;

import java.sql.Struct;

public class IdentityMatrix implements Visitor{
    private String[][] matrix;
    private int rows = 1;
    private int colums = 1;

    // Поэтапный вызов методов класса для редактирования его полей.
    @Override
    public void create(Collection struct) {
        stepA(struct);
        stepB(struct);
        stepC(struct);
        stepD();
    }

    // Узнать длинну списка узлов и создать квадратную матрицу размера Х+1.
    private void stepA(Collection struct){
        Iterator iteratorNode = struct.getIterator("Node");
        while (iteratorNode.hasNext()) {
            Node node = (Node) iteratorNode.next();
            colums++;
        }

        Iterator iteratorEgede = struct.getIterator("Egede");
        while (iteratorEgede.hasNext()) {
            Egede egede = (Egede) iteratorEgede.next();
            rows++;
        }

        matrix = new String[rows][colums];
    }

    // Заполнить получанный массив заголовками и "0".
    private void stepB(Collection struct){
        matrix[0][0] = "--";

        int index = 1;
        Iterator iteratorNode = struct.getIterator("Node");
        while (iteratorNode.hasNext()) {
            Node node = (Node) iteratorNode.next();
            matrix[0][index] = node.id;
            index++;
        }

        index = 1;
        Iterator iteratorEgede = struct.getIterator("Egede");
        while (iteratorEgede.hasNext()) {
            Egede egede = (Egede) iteratorEgede.next();
            matrix[index][0] = egede.getId();
            index++;
        }

        for (int i = 1; i < rows; i++)
            for (int j = 1; j < colums; j++){
                matrix[i][j] = "0";
            }
    }

    // Сопоставить заголовки и список связей, создать матрицу смежности.
    private void stepC(Collection struct){
        int index = 1;
        Iterator iteratorEgede = struct.getIterator("Egede");

        while (iteratorEgede.hasNext()){
            Egede egede = (Egede) iteratorEgede.next();
            for (int i = 1; i < colums; i++) {
                if ((egede.getSorse().equalsIgnoreCase(matrix[0][i]))||(egede.getReceiver().equalsIgnoreCase(matrix[0][i]))){
                     matrix[index][i] = "1";
                }
            }
            index++;
        }
    }

    // Вывести матрицу смежности в консоль.
    private void stepD(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
