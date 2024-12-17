package controllers;
import java.util.HashMap;
import java.util.function.Consumer;

import interactors.UserInteractor;
import javafx.util.Builder;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import models.MainModel;
import models.UserModel;
import views.LoginViewBuilder;
import views.EmpleadoViewBuilder;
public class UserController {
    private  Builder<Region> viewBuilder;
    private final UserInteractor interactor;
   
    private MainModel mainModel ;
    private BooleanProperty logged = new SimpleBooleanProperty(false);


    public UserController(MainModel mainModel){
        UserModel model = new UserModel();
        this.mainModel = mainModel;
        interactor = new UserInteractor(model);
        HashMap<String, Consumer<Runnable>> map =new HashMap<String, Consumer<Runnable>>();
        map.put("login",this::loginUser ); 
        map.put("register", this::registerUser);
        viewBuilder = new LoginViewBuilder(model,map );


    }

    public void loginUser(Runnable postAction){
        Region region = this.getView();
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() {
                interactor.loginUser();
                mainModel.loginSelectedProperty().set(false);
                mainModel.empleadoSelectedProperty().set(true);
                return null;
            }
        };
        saveTask.setOnSucceeded(evt -> {
        
            postAction.run();
        });

        Thread saveThread = new Thread(saveTask);
        saveThread.start();

    }

    public void registerUser(Runnable postAction){
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() {
                interactor.registerUser();
                return null;
            }
        };
        saveTask.setOnSucceeded(evt -> {
        
            postAction.run();
        });
        Thread saveThread = new Thread(saveTask);
        saveThread.start();
    }

    public void saludateUser(Runnable postAction){
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() {
               System.out.println("Hola usuarios");
                return null;
            }
        };
        saveTask.setOnSucceeded(evt -> {
        
            postAction.run();
        });
        Thread saveThread = new Thread(saveTask);
        saveThread.start();
    }



    public Region getView() {
        return viewBuilder.build();

    }
    
}
