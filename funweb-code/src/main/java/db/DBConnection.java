package db;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    String jdbcUrl = " jdbc:oracle:thin:@127.0.0.1:1521:XE";
    String userid = "tw2017";
    String password = "tw2017";
    Connection conn;

    public DBConnection() {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        OracleDataSource ds = null;

        try {
            ds = new OracleDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ds.setURL(jdbcUrl);

        try {
            conn = ds.getConnection(userid, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getDBConnection() throws SQLException {
        return conn;
    }
}

