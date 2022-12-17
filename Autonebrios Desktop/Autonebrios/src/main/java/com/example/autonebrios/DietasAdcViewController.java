package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Dieta;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DietasAdcViewController {
    DietasViewController ctrl;

    Helper h = new Helper();

    @FXML
    private TextField alimento1;

    @FXML
    private TextField qtd1;

    @FXML
    private TextField alimento2;

    @FXML
    private TextField qtd2;

    @FXML
    private TextField alimento3;

    @FXML
    private TextField qtd3;

    @FXML
    private TextField tempoTroca;

    @FXML
    private TextField tempoCriacao;

    @FXML
    private TextField descricao;

    @FXML
    private void btnSalvar(ActionEvent event) throws IOException {
        h.createDieta(new Dieta(alimento1.getText(), Double.parseDouble(qtd1.getText()), alimento2.getText(), Double.parseDouble(qtd2.getText()), alimento3.getText(), Double.parseDouble(qtd3.getText()), Integer.parseInt(tempoTroca.getText()), Integer.parseInt(tempoCriacao.getText()), descricao.getText()));

        alimento1.setText("");
        qtd1.setText("");
        alimento2.setText("");
        qtd2.setText("");
        alimento3.setText("");
        qtd3.setText("");
        tempoTroca.setText("");
        tempoCriacao.setText("");
        descricao.setText("");


        ctrl.initialize(null, null);
        Main.mudarTela("dietas");
    }

    @FXML
    private void voltar(ActionEvent evento) throws IOException {
        Main.mudarTela("dietas");
    }


}
