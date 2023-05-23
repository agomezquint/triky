package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public final class DBMovimientos {
    
    public boolean consultarMov(String movimiento) {
        Connection conDb = null;
        boolean respuesta = false;
        ResultSet rstDato = null;
        PreparedStatement prdStmt = null;
        String sql = "SELECT POSICION FROM movimiento WHERE POSICION=?";
        try {
            conDb = Conexion.CrearConexion();
            prdStmt = conDb.prepareStatement(sql);
            prdStmt.setString(1, movimiento);
            rstDato = prdStmt.executeQuery();
            while (rstDato.next()) {
                if (rstDato.getString(1).equals(movimiento)) {
                    respuesta = true;
                } 
            } 
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (rstDato != null) {
                    rstDato.close();
                }
                if (prdStmt != null) {
                    prdStmt.close();
                }
                if (conDb != null) {
                    conDb.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
        return respuesta;
    }
    
    public boolean insertarMov(String movimiento) {
        Connection conDb = null;
        boolean respuesta = false;
        PreparedStatement prdStmt = null;
        boolean existencia = false;
        existencia = consultarMov(movimiento);
 
        String sql = "INSERT INTO movimiento VALUES(?)";
        if (!existencia) {
            try {
                conDb = Conexion.CrearConexion();
                prdStmt = conDb.prepareStatement(sql);
                prdStmt.setString(1, movimiento);
                respuesta = prdStmt.executeUpdate() > 0;
            } catch (SQLException e) {
                System.out.println("Error: " + e);
            } finally {
                try {
                    if (prdStmt != null) {
                        prdStmt.close();
                    }
                    if (conDb != null) {
                        conDb.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e);
                }
            }
        }
        
        return respuesta;
    }
    
    public boolean borraMovimientos() {
        boolean respuesta = false;
        Connection conDb = null;
        PreparedStatement prdStmt = null;
        String sql = "TRUNCATE TABLE movimiento";
        try {
            conDb = Conexion.CrearConexion();
            prdStmt = conDb.prepareStatement(sql);
            respuesta = prdStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (prdStmt != null) {
                    prdStmt.close();
                }
                if (conDb != null) {
                    conDb.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
        return respuesta;
    }
}
