package com.company;

import java.sql.*;

class TaskDAO {
    static void add() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.h2.Driver").newInstance();

        Statement statement = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "").createStatement();

        statement.executeUpdate("INSERT INTO TEST VALUES ('HELLO')");

        statement.close();
    }

    static void getAll() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("org.h2.Driver").newInstance();

        Statement statement = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "").createStatement();

        statement.execute("SHOW COLUMNS FROM TEST;");

        statement.close();
    }

    static void remove() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class.forName("org.h2.Driver").newInstance();

        Statement statement = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "").createStatement();

        statement.executeUpdate("");

        statement.close();
    }
}
