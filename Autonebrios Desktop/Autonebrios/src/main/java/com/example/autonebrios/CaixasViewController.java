package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Caixa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CaixasViewController implements Initializable {
    Helper h = new Helper();
    CaixasAdcViewController caixasAdcViewController;

    @FXML
    private ListView<Parent> lvCaixas;

    private List<Caixa> caixas = h.getAllCaixas();

    private ObservableList<Parent> obsCaixas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("Xamp " + caixas);
        carregarCaixas();
    }

    public void carregarCaixas(){
        obsCaixas.clear();
        caixas = h.getAllCaixas();

        for (int i = 0; i < caixas.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CaixaCard.fxml"));

            try{
                Parent parent = fxmlLoader.load();

                CaixaCardController cardCtrol = fxmlLoader.getController();

                cardCtrol.setIndex(caixas.get(i).getId());

                cardCtrol.setIdT("Caixa " + caixas.get(i).getId());
                cardCtrol.setDataIT("Data de Início: " + caixas.get(i).getInicioCriacao());
                cardCtrol.setDataFT("Data de Conclusão: " + caixas.get(i).getFinalCriacao());
                cardCtrol.setDietaT("Dieta: " + caixas.get(i).getDieta());
                cardCtrol.setFuncaoT("Função: " + caixas.get(i).getFuncao());

                cardCtrol.caixasViewController = this;
                cardCtrol.caixasAdcViewController = this.caixasAdcViewController;

                obsCaixas.add(parent);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        lvCaixas.setItems(obsCaixas);
    }

    @FXML
    private void formAdc(ActionEvent evento) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CaixasAdcView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        CaixasAdcViewController caixasAdcViewController = fxmlLoader.getController();

        Main.stagee.setScene(scene);
    }

    @FXML
    private void voltar(ActionEvent evento) throws IOException {
        Main.mudarTela("main");
    }

}
