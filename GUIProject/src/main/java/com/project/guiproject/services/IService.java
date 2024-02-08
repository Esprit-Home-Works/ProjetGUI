package com.project.guiproject.services;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public void add(T t) throws SQLException;

    public void delete(int id) throws SQLException;

    public default void update(T t) throws SQLException {

    }

    public List<T> get() throws SQLException;

    public T getById(int id) throws SQLException;

}
