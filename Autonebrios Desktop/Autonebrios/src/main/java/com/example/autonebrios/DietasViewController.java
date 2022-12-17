package com.example.autonebrios;

import com.example.autonebrios.Models.Dieta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import com.example.autonebrios.Helpers.Helper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DietasViewController implements Initializable {
    Helper h = new Helper();

    @FXML
    private ListView<Parent> lvDietas;

    private List<Dieta> dietas = h.getAllDietas();

    private ObservableList<Parent> obsDietas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarDietas();
    }

    public void carregarDietas() {
        obsDietas.clear();
        dietas = h.getAllDietas();

        for (int i = 0; i < dietas.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DietaCard.fxml"));

            try{
                Parent parent = fxmlLoader.load();

                DietaCardController cardCtrol = fxmlLoader.getController();

                cardCtrol.setIndex(dietas.get(i).getId());

                cardCtrol.setIdT("Dieta " + dietas.get(i).getId());

                cardCtrol.setAl1T(dietas.get(i).getAlimento1() + " " + dietas.get(i).getQuantidade1() + "g");
                cardCtrol.setAl2T(dietas.get(i).getAlimento2() + " " + dietas.get(i).getQuantidade2() + "g");
                cardCtrol.setAl3T(dietas.get(i).getAlimento3() + " " + dietas.get(i).getQuantidade3() + "g");

                cardCtrol.setTrocasT("A cada " + dietas.get(i).getTempoDeTroca() + " dias");
                cardCtrol.setFimT("Retirada da produção em: " + dietas.get(i).getTempoTotal() + " dias");

                cardCtrol.setDescricaoT(dietas.get(i).getDescricao());

                cardCtrol.dietasViewController = this;

                obsDietas.add(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lvDietas.setItems(obsDietas);
    }

    @FXML
    private void formAdc(ActionEvent evento) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DietasAdcView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DietasAdcViewController dietasAdcViewController = fxmlLoader.getController();
        dietasAdcViewController.ctrl = this;
        Main.stagee.setScene(scene);
    }

    @FXML
    private void voltar(ActionEvent evento) throws IOException {
        Main.mudarTela("main");
    }




}
