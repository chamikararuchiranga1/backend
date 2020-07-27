package com.mvc.crud;

import com.mvc.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crud {
    public static PreparedStatement getPreaparedStatement(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < obj.length; i++) {
            pstm.setObject(i + 1, obj[i]);
        }
        return pstm;
    }

    public static ResultSet executeQuery(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        return getPreaparedStatement(sql, obj).executeQuery();
    }

    public static int executeUpdate(String sql, Object... obj) throws SQLException, ClassNotFoundException {
        return getPreaparedStatement(sql, obj).executeUpdate();

    }
}
