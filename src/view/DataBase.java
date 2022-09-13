package view;

import model.Item;
import model.Product;
import model.User;

import java.util.HashMap;
import java.util.Map;


public class DataBase {

    Map<Integer, User> users;
    Map<Integer, Item> estoque;

    public DataBase() {
        users = new HashMap<>();
        estoque = new HashMap<>();

        users.put(1, new User("Admin", "01689086467", "123456"));
        users.put(2, new User("Joaozinho", "66924428002", "123456"));
        users.put(3, new User("mariazinha", "45177944077", "123456"));
        users.put(4, new User("luluzinha", "93403252060", "123456"));

        estoque.put(1, new Item( new Product("Coffee und", 14.79), 3));
        estoque.put(2, new Item( new Product("Cheese kg", 16.90), 5));
        estoque.put(3, new Item( new Product("Butter und", 7.49), 5));
        estoque.put(4, new Item( new Product("Ham kg", 4.99), 5));
        estoque.put(5, new Item( new Product("Bread und", 0.50), 5));
    }

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Integer, User> users) {
        this.users = users;
    }

    public Map<Integer, Item> getEstoque() {
        return estoque;
    }

    public void setEstoque(Map<Integer, Item> estoque) {
        this.estoque = estoque;
    }
}
