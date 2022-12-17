package com.example.autonebrios;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    public static Stage stagee;

    private static Scene mainS;
    private static Scene dietasS;
    private static Scene dietasAdcS;
    private static Scene dietasEditS;
    private static Scene caixasS;
    private static Scene caixasAdcS;
    private static Scene caixasEditS;
    private static Scene eventosS;

    @Override
    public void start(Stage stage) throws IOException {
        stagee = stage;

        Parent mainV = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        mainS = new Scene(mainV);

        Parent dietasV = FXMLLoader.load(getClass().getResource("DietasView.fxml"));
        dietasS = new Scene(dietasV);

        Parent dietasAdcV = FXMLLoader.load(getClass().getResource("DietasAdcView.fxml"));
        dietasAdcS = new Scene(dietasAdcV);

        Parent dietasEditV = FXMLLoader.load(getClass().getResource("DietasEditView.fxml"));
        dietasEditS = new Scene(dietasEditV);

        Parent caixasV = FXMLLoader.load(getClass().getResource("CaixasView.fxml"));
        caixasS = new Scene(caixasV);

        Parent caixasAdcV = FXMLLoader.load(getClass().getResource("CaixasAdcView.fxml"));
        caixasAdcS = new Scene(caixasAdcV);

        Parent caixasEditV = FXMLLoader.load(getClass().getResource("CaixasEditView.fxml"));
        caixasEditS = new Scene(caixasEditV);

        Parent eventosV = FXMLLoader.load(getClass().getResource("EventosView.fxml"));
        eventosS = new Scene(eventosV);

        stage.setScene(mainS);
        stage.show();
    }

    public static void mudarTela(String scr){
        switch (scr){
            case "main":
                stagee.setScene(mainS);
                break;
            case "dietas":
                stagee.setScene(dietasS);
                break;
            case "dietasAdc":
                stagee.setScene(dietasAdcS);
                break;
            case "dietasEdit":
                stagee.setScene(dietasEditS);
                break;
            case "caixas":
                stagee.setScene(caixasS);
                break;
            case "caixasAdc":
                stagee.setScene(caixasAdcS);
                break;
            case "caixasEdit":
                stagee.setScene(caixasEditS);
                break;
            case "eventos":
                stagee.setScene(eventosS);
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}