package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EventosViewController implements Initializable {
    Helper h = new Helper();
    CaixasAdcViewController caixasAdcViewController;

    @FXML
    private ListView<Parent> lvEventos;

    private List<Evento> eventos = h.getAllEventos();

    private ObservableList<Parent> obsEventos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarEventos();
    }

    public void carregarEventos() {
        obsEventos.clear();
        eventos = h.getAllEventos();

        for (int i = 0; i < eventos.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EventosCard.fxml"));

            try{
                Parent parent = fxmlLoader.load();

                EventoCardController cardCtrol = fxmlLoader.getController();

                cardCtrol.setIndex(eventos.get(i).getId());

                cardCtrol.setIdT("Caixa " + eventos.get(i).getIdCaixa());

                cardCtrol.setDataT(eventos.get(i).getData());
                cardCtrol.setUrgenciaT("Urgência: " + eventos.get(i).getUrgencia());

                cardCtrol.setDescricaoT(eventos.get(i).getDescricao());

                cardCtrol.eventosViewController = this;

                obsEventos.add(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        lvEventos.setItems(obsEventos);
    }

    @FXML
    private void voltar(ActionEvent evento) throws IOException {
        Main.mudarTela("main");
    }
}
