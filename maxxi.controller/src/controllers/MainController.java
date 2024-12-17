package controllers;

import javafx.scene.layout.Region;
import javafx.util.Builder;
import models.MainModel;
import views.MainViewBuilder;

public class MainController {
          private final Builder<Region> viewBuilder;
    public MainController(){
        MainModel model = new MainModel();
        viewBuilder = new MainViewBuilder(model, 
        new UserController(model).getView(),
        new EmpleadoController().getView());
    }

    public Region getView(){
        return viewBuilder.build();
    }


}
