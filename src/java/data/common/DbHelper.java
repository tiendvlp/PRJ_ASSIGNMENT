package data.common;

import common.Config;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author dangminhtien
 */
public class DbHelper {
    private static BasicDataSource dataSource = null;
    
    public static Connection connect () throws ClassNotFoundException, SQLException {
        if (dataSource == null) {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            dataSource = new BasicDataSource();
            dataSource.setUrl(Config.DB_URL);
            dataSource.setUsername(Config.USER_NAME);
            dataSource.setPassword(Config.PASSWORD);
            dataSource.setMinIdle(5);
            dataSource.setMaxIdle(10);
            dataSource.setMaxTotal(25);
        }
        return dataSource.getConnection();
    }
}
