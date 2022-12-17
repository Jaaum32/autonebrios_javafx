package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Dieta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DietaCardController implements Initializable {
    Helper h = new Helper();

    int index = 0;

    @FXML
    private Text id;

    @FXML
    private Text alimento1;
    @FXML
    private Text alimento2;
    @FXML
    private Text alimento3;

    @FXML
    private Text trocas;
    @FXML
    private Text fim;

    @FXML
    private Text descricao;

    @FXML
    private void btnExcluirOnclick() {
        if (h.getSomeCaixas(h.getDieta(index).getId()).size() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Você não pode excluir uma dieta que está sendo utilizada em alguma caixa!");
            alert.showAndWait();
        } else {
            h.deleteDieta(this.index);
        }

        URL url = null;
        ResourceBundle resourceBundle = null;
        dietasViewController.initialize(url, resourceBundle);
    }

    @FXML
    private void btnEditarOnclick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DietasEditView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        DietasEditViewController dietasEditViewController = fxmlLoader.getController();

        dietasEditViewController.setIndex(this.index);

        dietasEditViewController.dietaCardController = this;
        dietasEditViewController.dietasViewController = this.dietasViewController;

        Main.stagee.setScene(scene);
    }

    String idT = "2";
    String al1T = "2";
    String al2T = "2";
    String al3T = "2";
    String trocasT = "2";
    String fimT = "2";
    String descricaoT = "2";

    DietasViewController dietasViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(idT);
        alimento1.setText(al1T);
        alimento2.setText(al2T);
        alimento3.setText(al3T);
        trocas.setText(trocasT);
        fim.setText(fimT);
        descricao.setText(descricaoT);
    }

    public void setIdT(String idT) {
        this.idT = idT;
        id.setText(idT);
    }

    public void setAl1T(String al1T) {
        this.al1T = al1T;
        alimento1.setText(al1T);
    }

    public void setAl2T(String al2T) {
        this.al2T = al2T;
        alimento2.setText(al2T);
    }

    public void setAl3T(String al3T) {
        this.al3T = al3T;
        alimento3.setText(al3T);
    }

    public void setTrocasT(String trocasT) {
        this.trocasT = trocasT;
        trocas.setText(trocasT);
    }

    public void setFimT(String fimT) {
        this.fimT = fimT;
        fim.setText(fimT);
    }

    public void setDescricaoT(String descricaoT) {
        this.descricaoT = descricaoT;
        descricao.setText(descricaoT);
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
