package interactors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

import models.MainModel;
import models.UserModel;
public class UserInteractor {
    private UserModel model;
    private Conexion conexion;
    
    public UserInteractor(UserModel model){
        this.model = model;
        this.conexion = new Conexion();
    }

    public void saveUser(){
        System.out.println("Nombre : " + model.getName()+"\n"
        + "Email : "+ model.getEmail()+ "\n"+
        "Password: "+model.getPassword()
        );
    }

    public void loginUser(){
        try {   
          ResultSet  resultSet = this.all();
            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // Cambia según los nombres de tus columnas
                String email = resultSet.getString("email");
                String passwordDb = resultSet.getString("password");
                if(email.equals(model.getEmail()) && passwordDb.equals(model.getPassword())){
                    System.out.println("Logeado!");
                    
                    return;
                }
                System.out.println("ID: " + id + ", Email: " + email + ", Password: " + passwordDb);
            }

            System.out.println("Credenciales no validas");
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("No pudimos logearte correctamente");
        }
        

    }

    public void registerUser(){
        try {   
            
            this.insert();
              
          } catch (SQLException e) {
              e.printStackTrace();
            
          }
          
    }




    //---Querys
    private ResultSet all() throws SQLException{
        String query = "SELECT * FROM users";
        Statement statement = conexion.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    private void insert() throws SQLException {
        String query =  "INSERT INTO users (email, password) VALUES (?, ?)";
        PreparedStatement  statement = this.conexion.getConnection().prepareStatement(query);
        statement.setString(1, this.model.getEmail());
        statement.setString(2, this.model.getPassword());
        int rowsInserted = statement.executeUpdate();

        if(rowsInserted > 0){
            System.out.println("Usuario registrado correctamente");
        } else{
            throw new SQLException("No se pudo registar el usuario");
        }

    }
    
}
