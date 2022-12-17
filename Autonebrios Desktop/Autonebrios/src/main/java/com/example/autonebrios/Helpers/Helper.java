package com.example.autonebrios.Helpers;

import com.example.autonebrios.Models.Caixa;
import com.example.autonebrios.Models.Dieta;
import com.example.autonebrios.Models.Evento;

import java.sql.*;
import java.util.ArrayList;

public class Helper {

    private final String dbName = "autonebrios";
    private final String dbUser = "root";
    private final String dbPass = "0406";
    private final String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;

    private Connection databaseLink;
    private static Helper INSTANCE;

    public Helper() {
        this.connect();
    }

    public static Helper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Helper();
        }
        return INSTANCE;
    }

    private void connect() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(dbUrl, dbUser, dbPass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public Connection getDatabaseLink() {
        return databaseLink;
    }

    //create
    public void createDieta(Dieta dieta) {

        try {
            PreparedStatement ps = databaseLink.prepareStatement("INSERT INTO dieta (alimento1, quantidade1, alimento2, quantidade2, alimento3, quantidade3, tempoDeTroca, tempoTotal, descricao) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, dieta.getAlimento1());
            ps.setDouble(2, dieta.getQuantidade1());
            ps.setString(3, dieta.getAlimento2());
            ps.setDouble(4, dieta.getQuantidade2());
            ps.setString(5, dieta.getAlimento3());
            ps.setDouble(6, dieta.getQuantidade3());
            ps.setInt(7, dieta.getTempoDeTroca());
            ps.setInt(8, dieta.getTempoTotal());
            ps.setString(9, dieta.getDescricao());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createCaixa(Caixa caixa) {

        try {
            PreparedStatement ps = databaseLink.prepareStatement("INSERT INTO caixa (inicioCriacao, finalCriacao, dieta, funcao) VALUES (?,?,?,?)");
            ps.setString(1, caixa.getInicioCriacao());
            ps.setString(2, caixa.getFinalCriacao());
            ps.setInt(3, caixa.getDieta());
            ps.setString(4, caixa.getFuncao());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createEvento(Evento evento) {

        try {
            PreparedStatement ps = databaseLink.prepareStatement("INSERT INTO evento (idCaixa, urgencia, dataE, descricao) VALUES (?, ?, ?, ?)");
            ps.setInt(1, evento.getIdCaixa());
            ps.setString(2, evento.getUrgencia());
            ps.setString(3, evento.getData());
            ps.setString(4, evento.getDescricao());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //read
    public ArrayList<Dieta> getAllDietas() {
        ArrayList<Dieta> dietas = new ArrayList<Dieta>();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from dieta");
            pre.execute();
            ResultSet rs = pre.getResultSet();
            while (rs.next()) {

                Dieta d = new Dieta();

                d.setId(rs.getInt("id"));
                d.setAlimento1(rs.getString("alimento1"));
                d.setQuantidade1(rs.getDouble("quantidade1"));
                d.setAlimento2(rs.getString("alimento2"));
                d.setQuantidade2(rs.getDouble("quantidade2"));
                d.setAlimento3(rs.getString("alimento3"));
                d.setQuantidade3(rs.getDouble("quantidade3"));
                d.setTempoDeTroca(rs.getInt("tempoDeTroca"));
                d.setTempoTotal(rs.getInt("tempoTotal"));
                d.setDescricao(rs.getString("descricao"));

                dietas.add(d);

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dietas;
    }

    public ArrayList<Caixa> getAllCaixas() {
        ArrayList<Caixa> caixas = new ArrayList<Caixa>();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from caixa");
            pre.execute();
            
            ResultSet rs = pre.getResultSet();
            while (rs.next()) {

                Caixa c = new Caixa();

                c.setId(rs.getInt("id"));
                c.setInicioCriacao(rs.getString("inicioCriacao"));
                c.setFinalCriacao(rs.getString("finalCriacao"));
                c.setDieta(rs.getInt("dieta"));
                c.setFuncao(rs.getString("funcao"));
                
                caixas.add(c);

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return caixas;
    }

    public ArrayList<Evento> getAllEventos() {
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from evento");
            pre.execute();
            
            ResultSet rs = pre.getResultSet();
            while (rs.next()) {

                Evento e = new Evento();

                e.setId(rs.getInt("id"));
                e.setIdCaixa(rs.getInt("idCaixa"));
                e.setUrgencia(rs.getString("urgencia"));
                e.setData(rs.getString("dataE"));
                e.setDescricao(rs.getString("descricao"));
                
                eventos.add(e);

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventos;
    }

    //search
    public Dieta getDieta(int id){
        Dieta d = new Dieta();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from dieta where id = ?");
            pre.setInt(1, id);
            pre.execute();
            ResultSet rs = pre.getResultSet();

            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setAlimento1(rs.getString("alimento1"));
                d.setQuantidade1(rs.getDouble("quantidade1"));
                d.setAlimento2(rs.getString("alimento2"));
                d.setQuantidade2(rs.getDouble("quantidade2"));
                d.setAlimento3(rs.getString("alimento3"));
                d.setQuantidade3(rs.getDouble("quantidade3"));
                d.setTempoDeTroca(rs.getInt("tempoDeTroca"));
                d.setTempoTotal(rs.getInt("tempoTotal"));
                d.setDescricao(rs.getString("descricao"));

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    public Caixa getCaixa(int id){
        Caixa c = new Caixa();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from caixa where id = ?");
            pre.setInt(1, id);
            pre.execute();
            ResultSet rs = pre.getResultSet();

            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setInicioCriacao(rs.getString("inicioCriacao"));
                c.setFinalCriacao(rs.getString("finalCriacao"));
                c.setDieta(rs.getInt("dieta"));
                c.setFuncao(rs.getString("funcao"));

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    public ArrayList<Caixa> getSomeCaixas(int dieta) {
        ArrayList<Caixa> caixas = new ArrayList<Caixa>();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from caixa where dieta = ?");
            pre.setInt(1, dieta);
            pre.execute();

            ResultSet rs = pre.getResultSet();
            while (rs.next()) {

                Caixa c = new Caixa();

                c.setId(rs.getInt("id"));
                c.setInicioCriacao(rs.getString("inicioCriacao"));
                c.setFinalCriacao(rs.getString("finalCriacao"));
                c.setDieta(rs.getInt("dieta"));
                c.setFuncao(rs.getString("funcao"));

                caixas.add(c);

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return caixas;
    }

    public ArrayList<Evento> getSomeEventos(int caixa) {
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        try {
            PreparedStatement pre = databaseLink.prepareStatement("select * from evento where idCaixa = ?");
            pre.setInt(1, caixa);
            pre.execute();

            ResultSet rs = pre.getResultSet();
            while (rs.next()) {

                Evento e = new Evento();

                e.setId(rs.getInt("id"));
                e.setIdCaixa(rs.getInt("idCaixa"));
                e.setUrgencia(rs.getString("urgencia"));
                e.setData(rs.getString("dataE"));
                e.setDescricao(rs.getString("descricao"));

                eventos.add(e);

                //System.out.println(rs.getString("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return eventos;
    }

    //update
    public void updateDieta(Dieta dieta) {
       // "INSERT INTO dieta (alimento1, quantidade1, alimento2, quantidade2, alimento3, quantidade3, tempoDeTroca, tempoTotal, descricao) VALUES (?,?,?,?,?,?,?,?,?)"
        
        try {
            PreparedStatement pre = databaseLink.prepareStatement("UPDATE dieta SET alimento1 = ?, quantidade1 = ?, alimento2 = ?, quantidade2 = ?, alimento3 = ?, quantidade3 = ?, tempoDeTroca = ?, tempoTotal = ?, descricao = ? WHERE id = ?");
            pre.setString(1, dieta.getAlimento1());
            pre.setDouble(2, dieta.getQuantidade1());
            pre.setString(3, dieta.getAlimento2());
            pre.setDouble(4, dieta.getQuantidade2());
            pre.setString(5, dieta.getAlimento3());
            pre.setDouble(6, dieta.getQuantidade3());
            pre.setInt(7, dieta.getTempoDeTroca());
            pre.setInt(8, dieta.getTempoTotal());
            pre.setString(9, dieta.getDescricao());
            pre.setLong(10, dieta.getId());
            pre.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCaixa(Caixa caixa) {
        try {
            PreparedStatement pre = databaseLink.prepareStatement("UPDATE caixa SET inicioCriacao = ?, finalCriacao = ?, dieta = ?, funcao = ? WHERE id = ?");
            pre.setString(1, caixa.getInicioCriacao());
            pre.setString(2, caixa.getFinalCriacao());
            pre.setInt(3, caixa.getDieta());
            pre.setString(4, caixa.getFuncao());
            pre.setInt(5, caixa.getId());

            pre.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEvento(Evento evento) {
        try {
            PreparedStatement pre = databaseLink.prepareStatement("UPDATE coleta SET idCaixa = ?, urgencia = ?, dataE = ?, descricao = ? WHERE id = ?");
            pre.setInt(1, evento.getIdCaixa());
            pre.setString(2, evento.getUrgencia());
            pre.setString(3, evento.getData());
            pre.setString(4, evento.getDescricao());
            pre.setInt(5, evento.getId());

            pre.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //delete
    public void deleteDieta(int id) {
        try {
            PreparedStatement pre = databaseLink.prepareStatement("DELETE FROM dieta WHERE id = ?");
            pre.setInt(1, id);

            pre.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCaixa(int id) {
        try {
            PreparedStatement pre = databaseLink.prepareStatement("DELETE FROM caixa WHERE id = ?");
            pre.setInt(1, id);

            pre.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEvento(int id) {
        try {
            PreparedStatement pre = databaseLink.prepareStatement("DELETE FROM evento WHERE id = ?");
            pre.setInt(1, id);

            pre.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
