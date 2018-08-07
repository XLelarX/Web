package com.company;

import java.sql.*;
import java.util.*;

class TaskDAO {
    private static final String PATH = "jdbc:h2:tcp://localhost/~/test";
    private static final String DELETE_ALL = "DELETE FROM TEST";
    private static final String INSERT = "INSERT INTO TEST VALUES ('%s')";
    private static final String SELECT = "SELECT * FROM TEST";
    private static final String DELETE = "DELETE FROM TEST WHERE NAME = '%s'";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String DRIVER = "org.h2.Driver";

    private static List<String> list = new ArrayList<String>();

    static void add(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Statement statement = getStatement();

        statement.execute(String.format(INSERT, str));
        statement.close();
    }

    private static Statement getStatement() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Class.forName(DRIVER).newInstance();

        return DriverManager.getConnection(PATH, USERNAME, PASSWORD).createStatement();
    }

    private static void getAll() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        list.clear();

        Statement statement = getStatement();

        statement.executeQuery(SELECT);
        ResultSet rs = statement.getResultSet();

        while (rs.next())
            list.add(rs.getString(1));

        statement.close();
    }

    static void remove(String str) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Statement statement = getStatement();

        statement.execute(String.format(DELETE, str));

        statement.close();
    }

    static void removeAll() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Statement statement = getStatement();

        statement.execute(DELETE_ALL);

        statement.close();
    }

    public static List<String> getList() {
        try {
            TaskDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
