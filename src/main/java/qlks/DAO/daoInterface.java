package qlks.DAO;

import java.util.List;

public interface daoInterface <E , T>{
    public void add(E e);

    public void update(E e);

    public void delete(T t);

    public List<E> selectAll();

    public E selectByID(T t);

    public List<E> selectBySql(String sql,Object...args);
}
