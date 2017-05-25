package db;

import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;

public class DBConnector {
    @Value("current.app.url")
    String dbUrl;

    @Value("current.app.sid")
    String dbSid;

    @Value("current.app.port")
    String dbPort;

    @Value("current.app.user")
    String dbUser;

    @Value("current.app.password")
    String dbPassword;

    String jdbcUrl = String.format("jdbc:oracle:thin:@%s:%s:%s", dbUrl, dbPort, dbSid);
    Connection conn;

    public DBConnector() {

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
            conn = ds.getConnection(dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getDBConnection() throws SQLException {
        return conn;
    }
}
