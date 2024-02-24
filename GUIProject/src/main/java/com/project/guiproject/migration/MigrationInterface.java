package com.project.guiproject.migration;

import com.project.guiproject.utils.MyDataBase;

import java.sql.Connection;
import java.sql.SQLException;

public interface MigrationInterface {
    Connection connection = MyDataBase.getInstace().getConnection();


    void migrate(Boolean remove) throws SQLException;

    void up() throws SQLException;

    void down() throws SQLException;
}
