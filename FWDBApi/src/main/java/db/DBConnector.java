package db;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class DBConnector {
    @Value("${current.app.url}")
    String dbUrl;

    @Value("${current.app.sid}")
    String dbSid;

    @Value("${current.app.port}")
    String dbPort;

    @Value("${current.app.user}")
    String dbUser;

    @Value("${current.app.password}")
    String dbPassword;

    String jdbcUrl = null;
    Connection conn;

    public DBConnector() {}

    @PostConstruct
    private void init() {

        jdbcUrl = String.format("jdbc:oracle:thin:@%s:%s:%s", dbUrl, dbPort, dbSid);

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
