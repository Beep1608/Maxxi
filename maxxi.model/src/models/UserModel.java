package models;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
public class UserModel {
    private final StringProperty email = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");;
    private final StringProperty role = new SimpleStringProperty("");;
    private final StringProperty name = new SimpleStringProperty("");;

    public String getEmail(){
        return email.get();
    }
    public StringProperty emailProperty(){
        return email;

    }

    public void setEmail(String email){
        this.email.set(email);
    }

    public String getPassword(){
        return password.get();
    }
    public StringProperty passwordProperty(){
        return password;

    }

    public void setPassword(String password){
        this.password.set(password);
    }

    public String getRole(){
        return role.get();
    }
    public StringProperty roleProperty(){
        return role;

    }

    public void setRole(String role){
        this.role.set(role);
    }

    public String getName(){
        return name.get();
    }
    public StringProperty nameProperty(){
        return name;

    }

    public void setName(String name){
        this.name.set(name);
    }



    

}
