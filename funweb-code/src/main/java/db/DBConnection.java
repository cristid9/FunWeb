package db;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DBConnection {

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
    }

    public Connection getDBConnection() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);

        return conn;
    }
}

