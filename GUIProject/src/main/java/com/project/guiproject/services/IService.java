package com.project.guiproject.services;

import com.project.guiproject.models.Match;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    public void add(T t) throws SQLException;

    public void delete(int id) throws SQLException;

    public void update(T t) throws SQLException;

    //Match getByCode(String code) throws SQLException;

    public List<T> get() throws SQLException;

    public T getById(int id) throws SQLException;

}
