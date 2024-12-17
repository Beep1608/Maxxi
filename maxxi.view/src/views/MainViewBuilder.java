package views;


import  javafx.util.Builder;
import models.MainModel;
import models.UserModel;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class MainViewBuilder implements Builder<Region> {
    private final MainModel model; 
   
    private final Region loginContent ;
    private final Region empleadoContent;

    public MainViewBuilder(MainModel model,   Region loginContent,  Region empleadoContent ){
        this.model = model;
        this.loginContent = loginContent;
        this.empleadoContent = empleadoContent;
    }
    
    @Override 
    public Region build(){
      
        StackPane results = (StackPane)this.createContent();



       return results;
    }

    private Node createContent(){
        loginContent.visibleProperty().bind(model.loginSelectedProperty());
        empleadoContent.visibleProperty().bind(model.empleadoSelectedProperty());
        return new StackPane(this.loginContent,this.empleadoContent);
    }
}
