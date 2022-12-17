package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EventoCardController implements Initializable {
    Helper h = new Helper();

    int index = 0;

    @FXML
    private Text data;

    @FXML
    private Text descricao;

    @FXML
    private Text id;

    @FXML
    private Text urgencia;

    @FXML
    void btnExcluirOnclick(ActionEvent event) {
        h.deleteEvento(this.index);

        URL url = null;
        ResourceBundle resourceBundle = null;
        eventosViewController.initialize(url, resourceBundle);
    }

    String idT = "2";
    String descricaoT = "2";
    String dataT = "2";
    String urgenciaT = "2";

    EventosViewController eventosViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(idT);
        descricao.setText(descricaoT);
        data.setText(dataT);
        urgencia.setText(urgenciaT);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setIdT(String idT) {
        this.idT = idT;
        id.setText(idT);
    }

    public void setDescricaoT(String descricaoT) {
        this.descricaoT = descricaoT;
        descricao.setText(descricaoT);
    }

    public void setDataT(String dataT) {
        this.dataT = dataT;
        data.setText(dataT);
    }

    public void setUrgenciaT(String urgenciaT) {
        this.urgenciaT = urgenciaT;
        urgencia.setText(urgenciaT);
    }
}
