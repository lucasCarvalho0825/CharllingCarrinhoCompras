package controller;

import model.Item;
import view.DataBase;
import view.Repository;

import java.util.ArrayList;
import java.util.List;

public class ItemController implements Repository<Item>  {

    DataBase dbLoja;
    public ItemController(DataBase dbLoja) {
        this.dbLoja = dbLoja;
    }

    @Override
    public void create(Item obj) {
        if( obj != null){
            dbLoja.getEstoque().put(obj.getId(), obj);
            return;
        }

        System.out.println(" There was an error trying to add the item");
    }

    @Override
    public Item findById(int id) {
        if(dbLoja.getEstoque().containsKey(id)){
            return dbLoja.getEstoque().get(id);
        }

        System.out.println("There was an error trying to find the item");
        return null;
    }

    @Override
    public List<Item> findAll() {
        if(dbLoja.getEstoque().isEmpty()){
            System.out.println(" The list is empty");
            return null;
        }

        return (List<Item>) new ArrayList<>(dbLoja.getEstoque().values());
    }

    @Override
    public void update(int id, Item obj) {
        if(dbLoja.getEstoque().containsKey(id)){
            dbLoja.getEstoque().put(id, obj);
        }

        System.out.println("There was an error trying to update the item");
    }

    @Override
    public void delete(int id) {
        if(dbLoja.getEstoque().containsKey(id)){
            dbLoja.getEstoque().remove(id);
            return;
        }

        System.out.println("There was an error trying to delete the item");

    }
}
