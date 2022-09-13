package view;

import java.util.List;

public interface Repository<T> {

    abstract void create(T obj);
    public T findById(int id);
    public List<T> findAll();
    public void update(int id, T obj);
    public void delete(int id);

}
