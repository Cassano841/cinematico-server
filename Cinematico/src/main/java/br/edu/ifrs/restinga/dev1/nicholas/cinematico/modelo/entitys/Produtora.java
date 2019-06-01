package br.edu.ifrs.restinga.dev1.nicholas.cinematico.modelo.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produtora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomeProdutora;
    private String localProdutora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProdutora() {
        return nomeProdutora;
    }

    public void setNomeProdutora(String nomeProdutora) {
        this.nomeProdutora = nomeProdutora;
    }

    public String getLocalProdutora() {
        return localProdutora;
    }

    public void setLocalProdutora(String localProdutora) {
        this.localProdutora = localProdutora;
    }
    
}
