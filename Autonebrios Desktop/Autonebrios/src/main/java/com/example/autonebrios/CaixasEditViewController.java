package com.example.autonebrios;

import com.example.autonebrios.Helpers.Helper;
import com.example.autonebrios.Models.Caixa;
import com.example.autonebrios.Models.Dieta;
import com.example.autonebrios.Models.Evento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

public class CaixasEditViewController implements Initializable {
    CaixaCardController caixaCardController;
    CaixasViewController caixasViewController;
    CaixasAdcViewController caixasAdcViewController;
    Helper h = new Helper();
    int index = 0;
    Caixa c = new Caixa();

    @FXML
    private TextField dataI;

    @FXML
    private TextField dieta;

    @FXML
    private TextField funcao;

    @FXML
    private Text title;

    String idT = "2";
    String dataIT = "2";
    String dataFT = "2";
    String dietaT = "2";
    String funcaoT = "2";

    @FXML
    void btnSalvar(ActionEvent event) throws ParseException, IOException {
        atualizar();


        MainViewController.att();
    }

    public void atualizar() throws ParseException {
        Dieta d = h.getDieta(Integer.parseInt(dieta.getText()));
        String dtF = calcDataFinal(dataI.getText(), d.getTempoTotal());

        c.setInicioCriacao(dataI.getText());
        c.setDieta(Integer.parseInt(dieta.getText()));
        c.setFuncao(funcao.getText());
        c.setFinalCriacao(dtF);

        h.updateCaixa(c);

        rmvEventos(h.getSomeEventos(index));
        adcEventos(d, c);
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        MainViewController.att();
        Main.mudarTela("caixas");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.c = h.getCaixa(this.index);
        this.idT = String.valueOf(c.getId());
        this.dataIT = c.getInicioCriacao();
        this.dataFT = c.getFinalCriacao();
        this.dietaT = String.valueOf(c.getDieta());
        this.funcaoT = c.getFuncao();

        title.setText("Caixa " + idT);
        dataI.setText(dataIT);
        dieta.setText(dietaT);
        funcao.setText(funcaoT);
    }

    public void setIndex(int index) {
        this.index = index;
        initialize(null, null);
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
        Date dtLast = Date.valueOf(last);

        LocalDate now = LocalDate.now();
        Date dtNow = Date.valueOf(now);

        int diff = (int) now.until(last, ChronoUnit.DAYS);

        return diff;
    }

}
