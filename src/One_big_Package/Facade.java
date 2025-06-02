package One_big_Package;

import java.util.Scanner;

// Фасад + Одиночка.

public class Facade {
    private static Facade facade;
    private Graph graph;
    private Menu menu;
    private MenuItem[] matrix;

    // Создаю элемент фасада (одиночку)
    private Facade(){
        menu = new Menu("Создание графа");
        graph = new Graph();
    }
    public static Facade getFacade(){ // добавить synchronized для многопоточки потом.
        if (facade == null) {
            facade = new Facade();
        }
        return facade;
    }

    // Создаю меню
    public void createMenu(){
        matrix = new MenuItem[6];
        // Создаем блюда для основного меню
        MenuItem createAutoGraf = new CreateDefaultGraph("Вывести граф по умолчанию [ 0 ]");
        matrix[0]=createAutoGraf;
        // todo придумать выход из цикла.
        MenuItem exit = new Exit("Выйти (Завершить работу программы) [ 5 ]");
        matrix[5]=exit;

        // Создаем подменю
        Menu persenGraf = new Menu("Создать свой граф");

        // Создаем блюда для подменю
        MenuItem a = new CreateNode("Ввести узел [ 1 ]");
        matrix[1]=a;
        MenuItem b = new CreateEdge("Ввести связь [ 2 ]");
        matrix[2]=b;
        MenuItem c = new CreateAdjacencyMatrix("Распечатать матрицу смежности [ 3 ]");
        matrix[3]=c;
        MenuItem d = new CreateIdentetyMatrix("Вывести матрицу инцидентности [ 4 ]");
        matrix[4]=d;

        // Добавляем блюда в подменю напитков
        persenGraf.add(a);
        persenGraf.add(b);
        persenGraf.add(c);
        persenGraf.add(d);

        // Добавляем блюда и подменю в основное меню
        menu.add(createAutoGraf);
        menu.add(persenGraf);
        menu.add(exit);
    }

    // Метод для работы с графом/ с меню.
    public void createGaph(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            MenuItem menuItem;
            // Отображаем содержимое основного меню
            menu.display(0);
            System.out.println();
            int key = Integer.parseInt(scanner.nextLine());

            menuItem = matrix[key];
            menuItem.execute(graph);
        }
    }
}
