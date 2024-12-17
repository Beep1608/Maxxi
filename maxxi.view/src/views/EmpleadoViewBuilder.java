package views;


import  javafx.util.Builder;
import models.UserModel;

import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class EmpleadoViewBuilder implements Builder<Region> {
    private final UserModel model; 
    private final  Consumer<Runnable> saveHandler;
    public EmpleadoViewBuilder(UserModel model, Consumer<Runnable> saveHandler){
        this.model = model;
        this.saveHandler = saveHandler;
    }
    
    @Override 
    public Region build(){
        
        HBox results = new HBox(createTextField("LOGEADOOOOOOOO"));
        results.getStylesheets().add("/empleadostyles.css");
        return  results; 
    }

    
    private Node createTextField(String promtText){

        TextField textField = new TextField();
        
        textField.setPrefWidth(300);
        textField.setPrefHeight(40);
        textField.setPromptText(promtText);
        textField.getStyleClass().add("text-field-login");

        return textField;


    }
}
