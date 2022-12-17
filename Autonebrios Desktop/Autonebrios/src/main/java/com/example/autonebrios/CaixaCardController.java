package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Evento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CaixaCardController implements Initializable {
    Helper h = new Helper();
    CaixasAdcViewController caixasAdcViewController;

    int index = 0;

    @FXML
    private Text dataF;

    @FXML
    private Text dataI;

    @FXML
    private Text dieta;

    @FXML
    private Text funcao;

    @FXML
    private Text id;

    @FXML
    void btnEditarOnclick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CaixasEditView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        CaixasEditViewController caixasEditViewController = fxmlLoader.getController();

        caixasEditViewController.setIndex(this.index);

        caixasEditViewController.caixaCardController = this;
        caixasEditViewController.caixasViewController = this.caixasViewController;

        Main.stagee.setScene(scene);
    }

    @FXML
    void btnExcluirOnclick(ActionEvent event) {
        h.deleteCaixa(this.index);
        rmvEventos(h.getSomeEventos(index));

        URL url = null;
        ResourceBundle resourceBundle = null;
        caixasViewController.initialize(url, resourceBundle);
    }

    String idT = "2";
    String dataIT = "2";
    String dataFT = "2";
    String dietaT = "2";
    String funcaoT = "2";

    CaixasViewController caixasViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(idT);
        dataI.setText(dataIT);
        dataF.setText(dataFT);
        dieta.setText(dietaT);
        funcao.setText(funcaoT);
    }

    public void rmvEventos(List<Evento> evts) {
        for (int i = 0; i < evts.size(); i++) {
            h.deleteEvento(evts.get(i).getId());
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setIdT(String idT) {
        this.idT = idT;
        id.setText(idT);
    }

    public void setDataIT(String dataIT) {
        this.dataIT = dataIT;
        dataI.setText(dataIT);
    }

    public void setDataFT(String dataFT) {
        this.dataFT = dataFT;
        dataF.setText(dataFT);
    }

    public void setDietaT(String dietaT) {
        this.dietaT = dietaT;
        dieta.setText(dietaT);
    }

    public void setFuncaoT(String funcaoT) {
        this.funcaoT = funcaoT;
        funcao.setText(funcaoT);
    }
}
