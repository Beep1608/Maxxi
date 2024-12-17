package interactors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
        String url = "jdbc:mysql://localhost:3306/maxxi"; // Cambia 'mi_base_datos' por el nombre de tu base
        String user = "root"; // Cambia si usas otro usuario
        String password = "12345"; // Cambia si tienes contraseña
        Connection connection =null;
        public Conexion(){
            try  {
                Connection connection = DriverManager.getConnection(url, user, password);
                this.connection = connection;
                System.out.println("¡Conexión exitosa a la base de datos!");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("Error al conectarse a la base de datos.");
                }
        }

        public Connection getConnection(){
            return this.connection;
        }

        public void finishConnection(){
            try {
                this.connection.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
                    System.err.println("Error al cerrar la conexion a la base de datos.");
            }
            
        }
    
}
