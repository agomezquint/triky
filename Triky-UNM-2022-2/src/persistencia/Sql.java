package persistencia;

import java.sql.*;

public class Sql {
    private String url;
    private String driver;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Sql(String url, String driver, String user, String password) {
        this.url = url;
        this.driver = driver;
        this.user = user;
        this.password = password;
        this.connection = null;
        this.statement = null;
        this.resultSet = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC.");
        }
    }

    public Connection makeConnection() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos.");
        }
        return this.connection;
    }

    public boolean closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.resultSet != null) {
                this.resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión.");
            return false;
        }
        return true;
    }

    public int update(String sql) throws SQLException {
        if (this.connection == null) {
            throw new SQLException("No se ha establecido una conexión con la base de datos.");
        }
        this.statement = this.connection.createStatement();
        return statement.executeUpdate(sql);
    }

    public ResultSet consult(String sql) throws SQLException {
        if (this.connection == null) {
            throw new SQLException("No se ha establecido una conexión con la base de datos.");
        }
        this.statement = this.connection.createStatement();
        this.resultSet = statement.executeQuery(sql);
        return resultSet;
    }
}
