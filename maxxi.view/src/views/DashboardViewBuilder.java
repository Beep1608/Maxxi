package views;

import  javafx.util.Builder;
import models.UserModel;

import java.util.function.Consumer;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;


public class DashboardViewBuilder implements Builder<Region> {
    private final UserModel model; 
    private final  Consumer<Runnable> saveHandler;
    public DashboardViewBuilder(UserModel model, Consumer<Runnable> saveHandler){
        this.model = model;
        this.saveHandler = saveHandler;
    }

    @Override 
    public Region build(){
        //Implemetar
        HBox results = new HBox( createSideBarContainer(),createContentContainer());
        results.getStylesheets().add("/dashboard.css");
        return  results; 
    }

    private Node createSideBarContainer(){
        HBox container = new HBox(createSideBar());
        
        container.setPrefWidth(500);
        container.getStyleClass().add("sidebar");
        return container;
    }
    private Node createSideBar(){
        VBox container = new VBox(createCompraOption(), createPrestamoOption());
   
        
        return container;
    }

    private Node createContentContainer(){
        HBox container = new HBox(createCompraOroContent());
          
          container.parentProperty().addListener(
            (obs,old, newVal) ->{
                if(newVal instanceof Region){
                    container.prefWidthProperty().bind(((Region) newVal).widthProperty());
                }
            }
        );
        container.getStyleClass().add("content");
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private Node createCompraOption(){
        VBox container = new VBox(new Label("Compra de oro"));
        container.setPrefHeight(100);
        container.getStyleClass().add("option");
        return  container;
    }

    private Node createCompraOroContent(){
        VBox container = new VBox(createKilatajeField(),createPrecioPagar());
        container.parentProperty().addListener(
            (obs,old, newVal) ->{
                if(newVal instanceof Region){
                    container.prefWidthProperty().bind(((Region) newVal).widthProperty().divide(2));
                }
            }
        );
        container.getStyleClass().add("compra-oro-content");
        return container;
    }

    private Node createKilatajeField(){
        TextField textField = new TextField();
        HBox container = new HBox();
        container.parentProperty().addListener(
            (obs,old, newVal) ->{
                if(newVal instanceof Region){
                    container.prefWidthProperty().bind(((Region) newVal).widthProperty().divide(1.5));
                    
                }
            }
        );

        textField.prefWidthProperty().bind(container.widthProperty().divide(2));
            container.getChildren().add(textField);
        
        container.getStyleClass().add("oro-container");

        return container;
    }

    private Node createPrecioPagar(){
        Label container = new Label("Precio a pagar: ");

        return container;
    }
    private Node createPrestamoOption(){
        VBox container = new VBox(new Label("Prestamo"));
        container.setPrefHeight(100);
        container.getStyleClass().add("option");
        return  container;
    }


}