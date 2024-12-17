package views;

import models.UserModel;
import javafx.scene.layout.Region;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.util.Builder;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.geometry.Pos;
import  javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.scene.control.PasswordField;
public class LoginViewBuilder implements Builder<Region> {
    private final UserModel model; 
    private final HashMap< String,Consumer<Runnable>> methodHashMap;
    private boolean switching = true;
    private double widthHeading = 0;
    private   BooleanProperty boolProperty = new SimpleBooleanProperty(true);
    private StringProperty stringProperty = new SimpleStringProperty("Sin cambio");


    public LoginViewBuilder(UserModel model, HashMap< String,Consumer<Runnable>> methodHashMap){
        this.model = model;
        this.methodHashMap = methodHashMap;
    }
    
    @Override 
    public Region build(){
        
        
     
        HBox results = new HBox(
        createLeftMainContainer(), 
        createRigthMainContainer());
        results.getStylesheets().add("/style.css");
        return  results; 
    }
    private Node createLeftMainContainer(){
        VBox container = new VBox(createInnerLeftContainer());
        container.getStyleClass().add("left-main");
        container.parentProperty().addListener(
            (obs,old, newVal) ->{
                if(newVal instanceof Region){
                    container.prefWidthProperty().bind(((Region) newVal).widthProperty().divide(3));
                }

                
            }
        );
        return container;

    }
    private Node createRigthMainContainer(){
        VBox container = new VBox(createInnerRigthContainer());
        container.getStyleClass().add("rigth-main");
        container.parentProperty().addListener(
            (obs,old, newVal) ->{
                if(newVal instanceof Region){
                    container.prefWidthProperty().bind(
                        ((Region) newVal).widthProperty()
                    );
                }
            }
        );

        container.setAlignment(Pos.CENTER);
        return container;

    }
    //LEFT------------------------------------------
    private Node createVerticalCenterLeftContainer(String css_class){
    
        VBox container = new VBox(
            createStyledLabel(
        "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum is simply dummy text of the printing and typesetting industryLorem Ipsum is simply dummy text of the printing and typesetting industryLorem Ipsum is simply dummy text of the printing and typesetting industry",
         "center-left-text"),
            createStyledLabel("MAXXI PRESTAMOS",
             "center-left-text-sub")
         );
         container.setAlignment(Pos.CENTER);
         container.setPadding(new Insets(0,30,0,30));
         container.setSpacing(25);
        container.getStyleClass().add(css_class);

        return container;

    }
    private Node createHorizontalTopLeftContainer(String css_class){
        Label label = new Label("Maxxi Prestamos");
        label.getStyleClass().add("top-name-logo");
        HBox container = new HBox(label);

        container.getStyleClass().add(css_class);

        return container;

    }
    private Node createHorizontalBottomLeftContainer(String css_class){
        HBox container = new HBox(
            createStyledLabel("2024", "bottom-date"),
            createStyledLabel("Nihao", "bottom-text")
        );
        container.getStyleClass().add(css_class);
        return container;
    }
   

    private Node createInnerLeftContainer(){
        BorderPane container = new BorderPane();
        container.setTop(createHorizontalTopLeftContainer("top-container"));
        container.setCenter(createVerticalCenterLeftContainer("center-container"));
        container.setBottom(createHorizontalBottomLeftContainer("bottom-container"));
        container.parentProperty().addListener((obs, oldParent, newParent) -> {
            if (newParent instanceof Region) {
                container.prefHeightProperty().bind(((Region) newParent).heightProperty());
            }
        });
        
        container.getStyleClass().add("left-main-inner");
        return container;


    }

    //--------------------------------------------------
    //RIGTH---------------------------------------------
    private Node createInnerRigthContainer(){
        VBox container = new VBox(createHeadingContainer(), createLoginContainer());
        boolProperty.addListener(
            (observable, oldValue, newValue) -> {
                System.out.println(container.getChildren());
                if (newValue) {
                    container.getChildren().remove(1); 
                    container.getChildren().add(createLoginContainer()); 
                }else{
                    container.getChildren().remove(1); 
                    container.getChildren().add(createRegisterContainer()); 
                }
            }

        );

        container.setSpacing(25);
        
        return container;
    }

    private Node createHeadingContainer(){
        HBox container = new HBox(createSwitchContainer());
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private Node createHeadingSwitchingBox(String css_cla){
        Label label = new Label("Iniciar Sesion");

        return label;
    }

    private Node createSwitchContainer(){
        HBox iniciarSesionBox  =(HBox) createSwichingBox("Inicar Sesion", "switching-label");
        HBox registroBox = (HBox)createSwichingBox("Registrarse", "switching-label");
        HBox container  = new HBox(iniciarSesionBox,registroBox);
        iniciarSesionBox.getStyleClass().add("active-switchingbox");
        iniciarSesionBox.getChildren().get(0).getStyleClass().add("active-switching");

        
        iniciarSesionBox.setOnMouseClicked(
            event->{
                boolProperty.set(true); 

                if(boolProperty.get()){
                    
                    iniciarSesionBox.getStyleClass().add("active-switchingbox");
                    iniciarSesionBox.getChildren().get(0).getStyleClass().add("active-switching");
                    registroBox.getStyleClass().removeAll("active-switchingbox");
                    registroBox.getChildren().get(0).getStyleClass().removeAll("active-switching");
                }
               
            }
        );

        registroBox.setOnMouseClicked(
            event->{
                boolProperty.set(false); 

                if(!boolProperty.get()){
                    registroBox.getStyleClass().add("active-switchingbox");
                    registroBox.getChildren().get(0).getStyleClass().add("active-switching");
                    iniciarSesionBox.getStyleClass().removeAll("active-switchingbox");
                    iniciarSesionBox.getChildren().get(0).getStyleClass().removeAll("active-switching");
                }
                System.out.println(widthHeading);
              
            }
        );
        container.setSpacing(25);
        
        return container;
    }
    private Node createSwichingBox(String text, String css_class){
        HBox container = new HBox (createStyledLabel(text, css_class));

        
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(10,10,10,10));
        container.getStyleClass().add("switchbox-container");
        widthHeading =  container.prefWidthProperty().getValue();
     

        return container;
    }


    private Node createLoginContainer(){
      
        HBox container = new HBox(
            createVBoxLoginContainer()
        ); 
        container.setAlignment(Pos.CENTER);
        return container;
    }
    private Node createVBoxLoginContainer(){
        TextField email = (TextField)createTextField("Email");
        TextField password = (PasswordField) createPasswordField("Password");
        this.model.emailProperty().bind(email.textProperty());
        this.model.passwordProperty().bind(password.textProperty());
        Button iniciarSesionButton =(Button)  createLoginButton("Iniciar Sesion");
        iniciarSesionButton.setOnAction(evt->{
            this.loginService().accept(()-> System.out.println("Login"));
        });
        VBox container  = new VBox(email,password ,
           iniciarSesionButton
        );
        container.setSpacing(25);

        return container;
    }
    private Node createVBoxRegisterContainer(){
        TextField name = (TextField) createTextField("Nombre completo");
        TextField email = (TextField) createTextField("Email");
        PasswordField password = (PasswordField) createPasswordField("Password");
        PasswordField confPassword = (PasswordField) createPasswordField("Confirm password");
        Button registerBtn = (Button) createLoginButton("Registrarse");

        this.model.emailProperty().bind(email.textProperty());
        this.model.passwordProperty().bind(password.textProperty());

        registerBtn.setOnAction(evt ->{
            this.registerService().accept(()->System.out.println("Registro"));
        });
        VBox container  = new VBox( name, email, password, confPassword, registerBtn);
        container.setSpacing(25);

        return container;
    }

    private Node createTextField(String promtText){

        TextField textField = new TextField();
        
        textField.setPrefWidth(300);
        textField.setPrefHeight(40);
        textField.setPromptText(promtText);
        textField.getStyleClass().add("text-field-login");

        return textField;


    }
    private Node createPasswordField(String promtText){

        PasswordField textField = new PasswordField();
        
        textField.setPrefWidth(300);
        textField.setPrefHeight(40);
        textField.setPromptText(promtText);
        textField.getStyleClass().add("text-field-login");

        return textField;


    }

    private Node createLoginButton(String text){
        Button btn = new Button(
           text
        );
        
        btn.setOnAction(evt ->{
            this.loginService();
        });

        btn.setPrefWidth(300);
        btn.setPrefHeight(40);
        btn.getStyleClass().add("login-btn");

        return btn;
    }

    private Node createRegisterContainer(){
        HBox container = new HBox(
            createVBoxRegisterContainer()
        ); 
        container.setAlignment(Pos.CENTER);
        return container;

    }



    //----------------------------------------------------



    private Node createStyledLabel(String text,String css_class){
        Label label = new Label(text);
        label.setWrapText(true); 
        label.getStyleClass().add(css_class);
        
        return label;
    }


    //Service Methods------------------------------------------------------

    private Consumer<Runnable> loginService (){
        return this.methodHashMap.get("login");
    }
    private Consumer<Runnable> registerService (){
        return this.methodHashMap.get("register");
    }
}