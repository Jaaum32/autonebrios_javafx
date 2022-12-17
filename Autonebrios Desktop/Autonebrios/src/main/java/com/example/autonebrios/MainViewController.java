package com.example.autonebrios;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainViewController implements Initializable {

    @FXML
    private void caixas(ActionEvent evento) throws IOException {
        att();
    }

    public static void att() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CaixasView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CaixasViewController caixasViewController = fxmlLoader.getController();
        caixasViewController.initialize(null, null);
        Main.stagee.setScene(scene);
    }

    @FXML
    private void eventos(ActionEvent evento) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EventosView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        EventosViewController eventosViewController = fxmlLoader.getController();
        eventosViewController.initialize(null, null);
        Main.stagee.setScene(scene);
    }

    @FXML
    private void dietas(ActionEvent evento) throws IOException {
        Main.mudarTela("dietas");
    }

    @FXML
    private void instrucoes(ActionEvent evento) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText("Nada por aqui!");
        alert.showAndWait();

        //Main.mudarTela("instrucoes");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
