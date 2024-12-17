package models;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class MainModel {
    private  final BooleanProperty loginProperty = new SimpleBooleanProperty(true);
    private  final BooleanProperty empleadoPorperty = new SimpleBooleanProperty(false);

    public MainModel(){
        
    }


    public  BooleanProperty loginSelectedProperty(){
        return loginProperty;
    
    }

    public  BooleanProperty empleadoSelectedProperty(){
        return empleadoPorperty;
    }
}
