package com.example.autonebrios.Models;

/**
 *
 * @author Jaum
 */

public class Evento {
    private int id;
    private int idCaixa;
    private String urgencia;
    private String data;
    private String descricao;

    public Evento() {
        
    }

    public Evento(int idCaixa, String urgencia, String data, String descricao) {
        this.idCaixa = idCaixa;
        this.urgencia = urgencia;
        this.data = data;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public String getUrgencia() {
        return urgencia;
    }

    public void setUrgencia(String urgencia) {
        this.urgencia = urgencia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
