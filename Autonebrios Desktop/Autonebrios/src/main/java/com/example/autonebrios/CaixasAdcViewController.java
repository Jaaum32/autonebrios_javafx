package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Caixa;
import com.example.autonebrios.Models.Dieta;
import com.example.autonebrios.Models.Evento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class CaixasAdcViewController {
    Helper h = new Helper();

    @FXML
    private TextField dataI;

    @FXML
    private TextField dieta;

    @FXML
    private TextField funcao;

    @FXML
    void btnSalvar(ActionEvent event) throws ParseException, IOException {
        Dieta d = h.getDieta(Integer.parseInt(dieta.getText()));
        String dtF = calcDataFinal(dataI.getText(), d.getTempoTotal());

        h.createCaixa(new Caixa(dataI.getText(), dtF, Integer.parseInt(dieta.getText()), funcao.getText()));

        Caixa cx = h.getAllCaixas().get(h.getAllCaixas().size() - 1);

        adcEventos(d, cx);

        dataI.setText("");
        dieta.setText("");
        funcao.setText("");

        MainViewController.att();
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MainViewController.att();
    }

    public String calcDataFinal(String dtI, int duracao) throws ParseException {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dtI, parser);

        data = data.plusDays(duracao);

        Date dt = Date.valueOf(data);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String dataF = sdf.format(dt);

        return dataF;
    }

    public void adcEventos(Dieta d, Caixa cx) throws ParseException {
        adcEventoTroca(d, cx);
        adcEventoFinal(d, cx);
    }

    public void rmvEventos(List<Evento> evts) {
        for (int i = 0; i < evts.size(); i++) {
            h.deleteEvento(evts.get(i).getId());
        }
    }

    public int definirIntervalos(Dieta d) {
        int I = 0;

        I = d.getTempoTotal() / d.getTempoDeTroca();

        return I;
    }

    public void adcEventoTroca(Dieta d, Caixa cx) throws ParseException {
        int eIdCaixa = 0;
        String eData = "";
        String eUrgencia = "";
        String eDescricao = "";

        String dataAux = dataI.getText();

        for (int i = 0; i < definirIntervalos(d) - 1; i++) {
            eIdCaixa = cx.getId();

            eData = calcDataFinal(dataAux, d.getTempoDeTroca());
            dataAux = eData;

            int aux = tempoFaltante(eData);

            if (aux > 60) {
                eUrgencia = "Sem pressa";
            } else if (aux <= 60 && aux > 30) {
                eUrgencia = "Falta muito";
            } else if (aux <= 30 && aux > 15) {
                eUrgencia = "Falta pouco";
            } else if (aux <= 15 && aux > 5) {
                eUrgencia = "Atenção";
            } else if (aux <= 5 && aux >= 0) {
                eUrgencia = "Urgente";
            } else {
                eUrgencia = "Atrasado";
            }

            if (eUrgencia != "Atrasado") {
                eDescricao = "Você deve trocar o alimento daqui a " + aux + " dias, no dia " + eData;
            } else {
                eDescricao = "Esta atividade está atrasada!";
            }

            h.createEvento(new Evento(eIdCaixa, eUrgencia, eData, eDescricao));
        }
    }

    public void adcEventoFinal(Dieta d, Caixa cx) throws ParseException {
        int eIdCaixa = 0;
        String eData = "";
        String eUrgencia = "";
        String eDescricao = "";

        eIdCaixa = cx.getId();

        eData = calcDataFinal(dataI.getText(), d.getTempoTotal());

        int aux = tempoFaltante(eData);

        if (aux > 60) {
            eUrgencia = "Sem pressa";
        } else if (aux <= 60 && aux > 30) {
            eUrgencia = "Falta muito";
        } else if (aux <= 30 && aux > 15) {
            eUrgencia = "Falta pouco";
        } else if (aux <= 15 && aux > 5) {
            eUrgencia = "Atenção";
        } else if (aux <= 5 && aux >= 0) {
            eUrgencia = "Urgente";
        } else {
            eUrgencia = "Atrasado";
        }

        if (eUrgencia != "Atrasado") {
            eDescricao = "Você deve coletar sua produção daqui a " + aux + " dias, no dia " + eData;
        } else {
            eDescricao = "Esta atividade está atrasada!";
        }

        h.createEvento(new Evento(eIdCaixa, eUrgencia, eData, eDescricao));
    }

    public int tempoFaltante(String eData) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate last = LocalDate.parse(eData, parser);

        LocalDate now = LocalDate.now();

        int diff = (int) now.until(last, ChronoUnit.DAYS);

        return diff;
    }


}
