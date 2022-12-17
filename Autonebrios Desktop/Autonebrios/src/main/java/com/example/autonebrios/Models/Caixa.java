package com.example.autonebrios.Models;

import java.io.Serializable;

/**
 *
 * @author Jaum
 */

public class Caixa implements Serializable{
    private int id;
    private String inicioCriacao;
    private String finalCriacao;
    private int dieta;
    private String funcao;

    public Caixa() {
    }

    public Caixa(String inicioCriacao, String finalCriacao, int dieta, String funcao) {
        this.inicioCriacao = inicioCriacao;
        this.finalCriacao = finalCriacao;
        this.dieta = dieta;
        this.funcao = funcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInicioCriacao() {
        return inicioCriacao;
    }

    public void setInicioCriacao(String inicioCriacao) {
        this.inicioCriacao = inicioCriacao;
    }

    public String getFinalCriacao() {
        return finalCriacao;
    }

    public void setFinalCriacao(String finalCriacao) {
        this.finalCriacao = finalCriacao;
    }

    public int getDieta() {
        return dieta;
    }

    public void setDieta(int dieta) {
        this.dieta = dieta;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "id=" + id +
                ", inicioCriacao='" + inicioCriacao + '\'' +
                ", finalCriacao='" + finalCriacao + '\'' +
                ", dieta=" + dieta +
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
