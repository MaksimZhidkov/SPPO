package One_big_Package;

public class AdjacencyMatrix implements Visitor{
    private String[][] matrix;
    private int len = 1;

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
        Iterator iterator = struct.getIterator("Node");
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            len++;
        }

        matrix = new String[len][len];
    }

    // Заполнить получанный массив заголовками и "0".
    private void stepB(Collection struct){
        matrix[0][0] = "-";

        int index = 1;
        Iterator iterator = struct.getIterator("Node");
        while (iterator.hasNext()) {
            Node node = (Node) iterator.next();
            matrix[0][index] = node.id;
            matrix[index][0] = node.id;
            index++;
        }

        for (int i = 1; i < len; i++)
            for (int j = i; j < len; j++){
                matrix[i][j] = "0";
                matrix[j][i] = "0";
            }
    }

    // Сопоставить заголовки и список связей, создать матрицу смежности.
    private void stepC(Collection struct){

        for (int i = 1; i < len; i++) {
            Iterator iterator = struct.getIterator("Egede");
            while (iterator.hasNext()){
                Egede egede = (Egede) iterator.next();
                for (int j = i; j < len; j++) {
                    if
                    ((egede.getSorse().equalsIgnoreCase(matrix[i][0]))&&(egede.getReceiver().equalsIgnoreCase(matrix[0][j]))||
                            (egede.getSorse().equalsIgnoreCase(matrix[0][j]))&&(egede.getReceiver().equalsIgnoreCase(matrix[i][0])))
                    {
                        matrix[i][j] = "1";
                        matrix[j][i] = "1";
                    }
                }
            }
        }
    }

    // Вывести матрицу смежности в консоль.
    private void stepD(){
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }
    }
}
