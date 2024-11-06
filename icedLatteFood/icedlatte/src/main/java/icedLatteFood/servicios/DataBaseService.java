package icedLatteFood.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DataBaseService {

    @Autowired
    private DataSource dataSource;

    public void testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connection successful: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
