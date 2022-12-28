package com.example.progettoispw.controllergrafici;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartApplication extends Application {

    //non mi piace l'instanziamento di 50 mila Controller Visulizzatore scene , riprendi quando hai fatto la singleton
    //adesso devo creare la pagina segnala palo e sotto di esso segnala buca
    @Override
    public void start(Stage stage) throws Exception {
        final String schermataPrincipale="prova-home.fxml";
        ControllerVisulizzatoreScene controllerVisulizzatoreScene=ControllerVisulizzatoreScene.getInstance(stage);
        controllerVisulizzatoreScene.visualizzaScenaPrincipale(schermataPrincipale);
    }
    public static void main(String[] args) {
        launch();
    }
}