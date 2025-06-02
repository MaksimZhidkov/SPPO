package One_big_Package;

import java.util.*;

// Класс для меню. Реализует паттерн Компоновщика.

public class Menu implements IMenuComponent {
    private final List<IMenuComponent> components = new ArrayList<>();
    private final String name;

    public Menu(String name) {
        this.name = name;
    }

    // Метод для добавления компонентов (блюд или подменю) в меню
    @Override
    public void add(IMenuComponent component) {
        components.add(component);
    }

    // Метод для удаления компонентов (блюд или подменю) из меню
    @Override
    public void remove(IMenuComponent component) {
        components.remove(component);
    }

    // Метод для отображения содержимого меню с отступом
    @Override
    public void display(int depth) {
        System.out.println("-".repeat(depth) + name);
        for (IMenuComponent component : components) {
            component.display(depth + 2);
        }
    }

}
