package One_big_Package;

// Общий интерфейс для компонентов (блюд и меню)
public interface IMenuComponent {
    void display(int depth);
    void add(IMenuComponent component);
    void remove(IMenuComponent component);
}
