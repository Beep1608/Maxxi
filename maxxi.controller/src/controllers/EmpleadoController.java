package controllers;

import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;
import interactors.UserInteractor;
import models.UserModel;
import views.DashboardViewBuilder;
import views.EmpleadoViewBuilder;
import views.LoginViewBuilder;

public class EmpleadoController {
    
        private final Builder<Region> viewBuilder;
    private final UserInteractor interactor;


    public EmpleadoController(){
        UserModel model = new UserModel();
        interactor = new UserInteractor(model);
        viewBuilder = new DashboardViewBuilder(model, this::saveUser);
        

    }
    
    public void saveUser(Runnable postAction){
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() {
               System.out.println("Hola Empleado");
               interactor.saveUser();
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
