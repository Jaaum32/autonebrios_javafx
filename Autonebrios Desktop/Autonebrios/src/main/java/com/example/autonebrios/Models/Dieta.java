package com.example.autonebrios.Models;

import java.io.Serializable;

/**
 *
 * @author Jaum
 */

public class Dieta implements Serializable {
    private int id;
    private String alimento1;
    private double quantidade1;
    private String alimento2;
    private double quantidade2;
    private String alimento3;
    private double quantidade3;
    private int tempoDeTroca;
    private int tempoTotal;
    private String descricao;

    public Dieta() {
        
    }

    public Dieta(String alimento1, double quantidade1, String alimento2, double quantidade2, String alimento3, double quantidade3, int tempoDeTroca, int tempoTotal, String descricao) {
        this.alimento1 = alimento1;
        this.quantidade1 = quantidade1;
        this.alimento2 = alimento2;
        this.quantidade2 = quantidade2;
        this.alimento3 = alimento3;
        this.quantidade3 = quantidade3;
        this.tempoDeTroca = tempoDeTroca;
        this.tempoTotal = tempoTotal;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlimento1() {
        return alimento1;
    }

    public void setAlimento1(String alimento1) {
        this.alimento1 = alimento1;
    }

    public double getQuantidade1() {
        return quantidade1;
    }

    public void setQuantidade1(double quantidade1) {
        this.quantidade1 = quantidade1;
    }

    public String getAlimento2() {
        return alimento2;
    }

    public void setAlimento2(String alimento2) {
        this.alimento2 = alimento2;
    }

    public double getQuantidade2() {
        return quantidade2;
    }

    public void setQuantidade2(double quantidade2) {
        this.quantidade2 = quantidade2;
    }

    public String getAlimento3() {
        return alimento3;
    }

    public void setAlimento3(String alimento3) {
        this.alimento3 = alimento3;
    }

    public double getQuantidade3() {
        return quantidade3;
    }

    public void setQuantidade3(double quantidade3) {
        this.quantidade3 = quantidade3;
    }

    public int getTempoDeTroca() {
        return tempoDeTroca;
    }

    public void setTempoDeTroca(int tempoDeTroca) {
        this.tempoDeTroca = tempoDeTroca;
    }

    public int getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(int tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Dieta{" +
                "id=" + id +
                ", alimento1='" + alimento1 + '\'' +
                ", quantidade1=" + quantidade1 +
                ", alimento2='" + alimento2 + '\'' +
                ", quantidade2=" + quantidade2 +
                ", alimento3='" + alimento3 + '\'' +
                ", quantidade3=" + quantidade3 +
                ", tempoDeTroca=" + tempoDeTroca +
                ", tempoTotal=" + tempoTotal +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
