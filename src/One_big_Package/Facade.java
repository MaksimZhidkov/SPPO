package One_big_Package;

import java.util.Scanner;

// Фасад + Одиночка.

public class Facade {
    private static Facade facade;
    private Graph graph;
    private Menu menu;

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
        // Создаем блюда для основного меню
        MenuItem createAutoGraf = new MenuItem("Вывести граф по умолчанию [ 0 ]");
        MenuItem exit = new MenuItem("Выйти (Завершить работу программы) [ -1 ]");

        // Создаем подменю
        Menu persenGraf = new Menu("Создать свой граф");

        // Создаем блюда для подменю
        MenuItem a = new MenuItem("Ввести узел [ 1 ]");
        MenuItem b = new MenuItem("Ввести связь [ 2 ]");
        MenuItem c = new MenuItem("Распечатать матрицу смежности [ 3 ]");
        MenuItem d = new MenuItem("Вывести матрицу инцидентности [ 4 ]");

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
        int counter = 1;
        while (counter == 1){
            // Отображаем содержимое основного меню
            menu.display(0);
            System.out.println();
            String key = scanner.nextLine();

            counter = menu.functionMenu(graph, key);
        }
    }

}
