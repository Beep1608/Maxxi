package core;

import controllers.MainController;
import controllers.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.Screen;
public class Main extends Application {
    
    public static void main(String[] args) {
        
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new MainController().getView()));
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}
