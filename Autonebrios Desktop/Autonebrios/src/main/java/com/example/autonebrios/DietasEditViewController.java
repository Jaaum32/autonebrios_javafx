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

public class DietasEditViewController implements Initializable {
    DietaCardController dietaCardController;
    DietasViewController dietasViewController;
    CaixasViewController caixasViewController;
    Helper h = new Helper();
    int index = 0;
    Dieta d = new Dieta();

    @FXML
    private Text title;

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

    String idT = "2";
    String al1T = "2";
    String qtd1T = "2";
    String al2T = "2";
    String qtd2T = "2";
    String al3T = "2";
    String qtd3T = "2";
    String trocasT = "2";
    String fimT = "2";
    String descricaoT = "2";

    @FXML
    private void btnSalvar(ActionEvent event) throws IOException, ParseException {
        d.setAlimento1(alimento1.getText());
        d.setQuantidade1(Double.parseDouble(qtd1.getText()));
        d.setAlimento2(alimento2.getText());
        d.setQuantidade2(Double.parseDouble(qtd2.getText()));
        d.setAlimento3(alimento3.getText());
        d.setQuantidade3(Double.parseDouble(qtd3.getText()));
        d.setTempoDeTroca(Integer.parseInt(tempoTroca.getText()));
        d.setTempoTotal(Integer.parseInt(tempoCriacao.getText()));
        d.setDescricao(descricao.getText());

        h.updateDieta(d);

        dietasViewController.initialize(null, null);

        List<Caixa> ds = h.getSomeCaixas(Integer.parseInt(idT));

        for (int i = 0; i < ds.size(); i++) {
            Caixa c = ds.get(i);
            Dieta d = h.getDieta(c.getDieta());
            String dtF = calcDataFinal(c.getInicioCriacao(), d.getTempoTotal());

            c.setFinalCriacao(dtF);

            h.updateCaixa(c);

            rmvEventos(h.getSomeEventos(c.getId()));
            adcEventos(d, c);

            //caixasViewController.initialize(null, null);
        }

        Main.mudarTela("dietas");
    }

    @FXML
    private void voltar(ActionEvent evento) throws IOException {
        Main.mudarTela("dietas");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(this.index);
        this.d = h.getDieta(this.index);
        System.out.println(d);
        this.idT = String.valueOf(d.getId());
        this.al1T = d.getAlimento1();
        this.qtd1T = String.valueOf(d.getQuantidade1());
        this.al2T = d.getAlimento2();
        this.qtd2T = String.valueOf(d.getQuantidade2());
        this.al3T = d.getAlimento3();
        this.qtd3T = String.valueOf(d.getQuantidade3());
        this.trocasT = String.valueOf(d.getTempoDeTroca());
        this.fimT = String.valueOf(d.getTempoTotal());
        this.descricaoT = d.getDescricao();

        title.setText("Dieta " + idT);
        alimento1.setText(al1T);
        qtd1.setText(qtd1T);
        alimento2.setText(al2T);
        qtd2.setText(qtd2T);
        alimento3.setText(al3T);
        qtd3.setText(qtd3T);
        tempoTroca.setText(trocasT);
        tempoCriacao.setText(fimT);
        descricao.setText(descricaoT);
    }

    public void setIndex(int index) {
        this.index = index;
        initialize(null, null);
    }

    public String calcDataFinal(String dtI, int duracao) throws ParseException {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dtI, parser);
        System.out.println("LocalDate " + data);

        data = data.plusDays(duracao);
        System.out.println("Plus " + data);

        Date dt = Date.valueOf(data);
        System.out.println("Date " + dt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String dataF = sdf.format(dt);
        System.out.println("String " + dataF);

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

        String dataAux = cx.getInicioCriacao();

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

        eData = calcDataFinal(cx.getInicioCriacao(), d.getTempoTotal());

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
