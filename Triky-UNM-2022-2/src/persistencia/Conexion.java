
package persistencia;

import java.sql.*;


public class Conexion {
    static Connection con;
    public static Connection CrearConexion() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String url = "jdbc:mysql://localhost:3307/triky";
            String password = "Master01*";
            con = DriverManager.getConnection(url, user, password);
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error: "+e);

        }
        return con;
    }
    
    
    
}
