package com.example.progettoispw.controllergrafici;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerVisulizzatoreScene{
    private Scene scene;
    private FXMLLoader loader;
    private Parent fxmlLoader;
    private static ControllerVisulizzatoreScene controllerVisulizzatoreScene=null;
    private static Stage stage;
    private String string;

    private ControllerVisulizzatoreScene(Stage newStage){
        stage=newStage;
    }
    public static ControllerVisulizzatoreScene getInstance(Stage newStage){
        if(controllerVisulizzatoreScene==null){
            controllerVisulizzatoreScene=new ControllerVisulizzatoreScene(newStage);
        }
        return controllerVisulizzatoreScene;
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
        fxmlLoader = FXMLLoader.load(getClass().getResource(stringScena));
        //stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader);
        stage.setScene(scene);
        stage.show();
    }
}
