package persistencia;

import java.sql.*;

/**
 * Ejemplo para conectarse a una base de datos.
 * Conexión a Bases de Datos.
 * 
 * Modificar los detalles de la conexión y las consultas según tu configuración.
 * 
 * Ejemplo de uso:
 * 
 * public static void main(String[] args) {
 *    try {
 *        Ejemplo ejemplo = new Ejemplo();
 *        ejemplo.consultando();
 *        ejemplo.modificando();
 *        ejemplo.consultando();
 *        ejemplo.eliminando();
 *        ejemplo.consultando();
 *        ejemplo.creando();
 *        ejemplo.consultando();
 *        ejemplo.cerrarConexion();
 *    } catch (SQLException e) {
 *        System.out.println("Error en la conexión: " + e.getMessage());
 *    }
 * }
 * 
 */
public final class Ejemplo {
    private final Connection conexion;

    public Ejemplo() throws SQLException {
        conexion = Conexion.CrearConexion();
        
        if (conexion == null) {
            System.out.println("Error al establecer la conexión.");
        } else {
            consultando();
            modificando();
            consultando();
            eliminando();
            consultando();
            creando();
            consultando();
        }
    }
    
    public void creando() throws SQLException {
        String sql = "INSERT INTO movimiento (POSICION) VALUES (?)";
        try (PreparedStatement prdStmt = conexion.prepareStatement(sql)) {
            prdStmt.setString(1, "Fulano de Tal");
            prdStmt.executeUpdate();
        }
    }
    
    public void modificando() throws SQLException {
        String sql = "UPDATE movimiento SET POSICION = ? WHERE POSICION = ?";
        try (PreparedStatement prdStmt = conexion.prepareStatement(sql)) {
            prdStmt.setString(1, "Maria Alejandra Buitrago Sanchez (MOD)");
            prdStmt.setString(2, "Fulano de Tal");
            prdStmt.executeUpdate();
        }
    }
    
    public void eliminando() throws SQLException {
        String sql = "DELETE FROM movimiento WHERE POSICION = ?";
        try (PreparedStatement prdStmt = conexion.prepareStatement(sql)) {
            prdStmt.setString(1, "Fulano de Tal");
            prdStmt.executeUpdate();
        }
    }
    
    public void consultando() throws SQLException {
        String sql = "SELECT * FROM movimiento";
        try (PreparedStatement prdStmt = conexion.prepareStatement(sql);
             ResultSet rst = prdStmt.executeQuery()) {
            System.out.println();
            while (rst.next()) {
                System.out.print(rst.getString("POSICION") + " ");
            }
            System.out.println();
        }
    }
    
    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            Ejemplo ejemplo = new Ejemplo();
            ejemplo.consultando();
            ejemplo.modificando();
            ejemplo.consultando();
            ejemplo.eliminando();
            ejemplo.consultando();
            ejemplo.creando();
            ejemplo.consultando();
            ejemplo.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
}
