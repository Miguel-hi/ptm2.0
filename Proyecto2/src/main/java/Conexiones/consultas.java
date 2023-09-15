package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class consultas {
    
    public void guardarUsuario(String TextCuenta, String TextUsuario, String TextCarrera, String TxTCorreo , char[] PassContra) throws ClassNotFoundException {
        ConexionDB db = new ConexionDB();
        //String sql = "insert into estudiante(id_estudiante, Nombre_Estudiante, Carrera_Estudiante, clave) values ('" + TextCuenta + "', '" + TextUsuario + "', '" + TextCarrera + "', '" + new String(PassContra) + "');";
        String sql = "INSERT INTO estudiante(id_estudiante, Nombre_Estudiante, Carrera_Estudiante, user_email, clave) VALUES (?, ?, ?, ?, ?);";
        Statement st;
        Connection conexion = db.conectar();
        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, TextCuenta);
            preparedStatement.setString(2, TextUsuario);
            preparedStatement.setString(3, TextCarrera);
            preparedStatement.setString(4, TxTCorreo);
            preparedStatement.setString(5, new String(PassContra));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Guardado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar el registro");
            }
            /* st = conexion.createStatement();
            int rs = st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Guardado correctamente"); */
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void consultarUsuario(String usuario, String pass) throws ClassNotFoundException {
        ConexionDB db = new ConexionDB();
        Connection conexion = db.conectar();

        String consulta = "SELECT * FROM estudiante WHERE id_estudiante = ? AND clave = ?";

        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales inválidas");
            }
        } catch (SQLException e) {
            System.out.println("Error al validar las credenciales: " + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
    

    public void consultarUsuarioAdmin(String usuario, String pass) throws ClassNotFoundException {
        ConexionDB db = new ConexionDB();
        Connection conexion = db.conectar();

        String consulta = "SELECT * FROM admin WHERE admin_id = ? AND admin_pwd = ?";

        try {
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales inválidas");
            }
        } catch (SQLException e) {
            System.out.println("Error al validar las credenciales: " + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    
}
