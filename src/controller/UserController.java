package controller;

import model.Cart;
import view.DataBase;
import model.User;
import view.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController implements Repository<User> {

    DataBase dbLoja;
    public UserController(DataBase dbLoja) {
        this.dbLoja = dbLoja;
    }


    @Override
    public void create(User obj) {
        if( obj != null){
            dbLoja.getUsers().put(obj.getId(), obj);
            return;
        }

        System.out.println("There was an error trying to add the user");
    }
    @Override
    public User findById(int id) {
        if(dbLoja.getUsers().containsKey(id)){
            return dbLoja.getUsers().get(id);
        }

        System.out.println("There was an error trying to find the user");
        return null;
    }
    public User findByCpf(String cpf){
        for(User u : dbLoja.getUsers().values()){
            if(cpf.equals(u.getCpf())){
                return u;
            }
        }

        System.out.println("There was an error trying to find the user");
        return null;
    }
    @Override
    public List<User> findAll() {
        if(dbLoja.getUsers().isEmpty()){
            System.out.println("The list is empty");
            return null;
        }
        return (List<User>) dbLoja.getUsers().values().stream().collect(
                Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void update(int id, User obj) {
        if(dbLoja.getUsers().containsKey(id)){
            dbLoja.getUsers().put(id, obj);
            return;
        }

        System.out.println("There was an error trying to update the user");
    }

    @Override
    public void delete(int id) {
        if(dbLoja.getUsers().containsKey(id)){
            dbLoja.getUsers().remove(id);
            return;
        }

        System.out.println("There was an error trying to delete the user");
    }

    public void showCart(int id){
        User u = findById(id);
        for(Cart c : u.getCarrinho()){
            System.out.println(c.toString());
        }

    }
}
