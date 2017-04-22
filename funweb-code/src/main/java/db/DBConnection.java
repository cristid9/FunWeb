package db;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {

    @Value("${db.url}")
    String dbUrl;

    @Value("${db.user}")
    String dbUser;

    @Value("${db.password}")
    String dbPass;

    @Value("${db.port}")
    Long dbPort;

    @Value("${db.sid}")
    String dbSid;

    String jdbcUrl = String.format("jdbc:oracle:thin:@%s:%d:%s", dbUrl, dbPort, dbSid);
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
            conn = ds.getConnection(dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getDBConnection() throws SQLException {
        return conn;
    }
}

