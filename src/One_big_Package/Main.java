package One_big_Package;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // Создаем блюда для основного меню
        MenuItem createAutoGraf = new MenuItem("Вывести граф по умолчанию [ 0 ]");
        MenuItem exit = new MenuItem("Выйти (Завершить работу программы) [ -1 ]");

        // Создаем подменю
        Menu persenGraf = new Menu("Создать свой граф [ 1 ]");

        // Создаем блюда для подменю
        MenuItem a = new MenuItem("Ввести узел");
        MenuItem b = new MenuItem("Ввести связь");
        MenuItem c = new MenuItem("Распечатать матрицу смежности");
        MenuItem d = new MenuItem("Вывести матрицу инцидентности");

        // Добавляем блюда в подменю напитков
        persenGraf.add(a);
        persenGraf.add(b);
        persenGraf.add(c);
        persenGraf.add(d);

        // Создаем меню
        Menu mainMenu = new Menu("Создание графа");

        // Добавляем блюда и подменю в основное меню
        mainMenu.add(createAutoGraf);
        mainMenu.add(persenGraf);
        mainMenu.add(exit);

        // Отображаем содержимое основного меню
        mainMenu.display(0);





        boolean flag = true;
        Scanner in = new Scanner(System.in);
        String key;

        while (flag) {
            key = in.next();
            if (key.equalsIgnoreCase("1")) {
                mainMenu.remove(createAutoGraf);
                mainMenu.remove(persenGraf);
                persenGraf.remove(a);
                persenGraf.remove(b);
                persenGraf.remove(c);
                persenGraf.remove(d);

                persenGraf = new Menu("Создать свой граф");
                mainMenu.add(persenGraf);

                a = new MenuItem("Ввести узел [ 1 ]");
                b = new MenuItem("Ввести связь [ 2 ]");
                c = new MenuItem("Распечатать матрицу смежности [ 3 ]");
                d = new MenuItem("Вывести матрицу инцидентности [ 4 ]");

                persenGraf.add(a);
                persenGraf.add(b);
                persenGraf.add(c);
                persenGraf.add(d);

                Graf graf = new Graf();

                while (true) {
                    mainMenu.display(0);
                    key = in.next();

                    if (key.equalsIgnoreCase("1")){
                        graf.addNode();
                    } else if (key.equalsIgnoreCase("2")){
                        graf.addEgede();
                    } else if (key.equalsIgnoreCase("3")){
                        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
                        graf.beBuild(adjacencyMatrix);
                    } else if (key.equalsIgnoreCase("4")) {
                        IdentityMatrix identityMatrix = new IdentityMatrix();
                        graf.beBuild(identityMatrix);
                    } else if (key.equalsIgnoreCase("-1")) {
                        break;
                    }
                }
                break;
            } else if (key.equalsIgnoreCase("0")) {
                Graf graf = new Graf();

                graf.addNode("A", "Node first");
                graf.addNode("B", "Node second");
                graf.addNode("C", "Node third");
                graf.addNode("D", "Node fourth");

                graf.addEgede("A", "B");
                graf.addEgede("A", "C");
                graf.addEgede("A", "D");
                graf.addEgede("C", "B");
                graf.addEgede("B", "D");

                AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix();
                IdentityMatrix identityMatrix = new IdentityMatrix();

                graf.beBuild(adjacencyMatrix);
                System.out.println();
                graf.beBuild(identityMatrix);
                flag = false;
            } else if (key.equalsIgnoreCase("-1")){
                flag = false;
            }
        }
    }
}