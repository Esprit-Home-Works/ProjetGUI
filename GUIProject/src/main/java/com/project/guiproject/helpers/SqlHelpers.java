package com.project.guiproject.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelpers {
	public static boolean tableExists(Connection connection, String tableName) throws SQLException {
		String query = "SELECT * FROM information_schema.tables WHERE table_schema = ? AND table_name = ? LIMIT 1;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, "projetgui");
		ps.setString(2, tableName);
		ResultSet rs = ps.executeQuery();

        return rs.next();
    }
}
