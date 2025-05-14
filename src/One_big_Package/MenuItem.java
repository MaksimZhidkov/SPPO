package One_big_Package;

// Класс для отдельных блюд.
public class MenuItem implements IMenuComponent {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    // Метод для добавления компонента (не используется для MenuItem)
    @Override
    public void add(IMenuComponent component) {
        throw new UnsupportedOperationException();
    }

    // Метод для удаления компонента (не используется для MenuItem)
    @Override
    public void remove(IMenuComponent component) {
        throw new UnsupportedOperationException();
    }

    // Метод для отображения блюда с отступом
    @Override
    public void display(int depth) {
        System.out.println("-".repeat(depth) + name);
    }
}
