package com.example.progettoispw.controllergrafici;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ControllerVisualizzatoreScene{
    private static ControllerVisualizzatoreScene controllerVisualizzatoreScene=null;
    private static Stage stage;

    private ControllerVisualizzatoreScene(Stage newStage){
        stage=newStage;
    }
    public static ControllerVisualizzatoreScene getInstance(Stage newStage){
        if(controllerVisualizzatoreScene==null){
            controllerVisualizzatoreScene=new ControllerVisualizzatoreScene(newStage);
        }
        return controllerVisualizzatoreScene;
    }


    public void visualizzaScenaPrincipale(String stringaScena) throws Exception {
        //this method load the main view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(stringaScena));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public  void visualizzaScena(String stringScena) throws Exception{
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(stringScena)));
        Scene scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
    }
}
