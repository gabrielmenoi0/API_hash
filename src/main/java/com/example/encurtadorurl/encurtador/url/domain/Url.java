package com.example.encurtadorurl.encurtador.url.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_url")
public class Url implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private  String senha;
    private  String hash;
    private String dateSave;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDateSave() {
        return dateSave;
    }

    public void setDateSave(String dateSave) {
        this.dateSave = dateSave;
    }

    public Url(UUID id, String senha, String hash, String dateSave) {
        this.id = id;
        this.senha = senha;
        this.hash = hash;
        this.dateSave = dateSave;
    }

    public Url() {
    }
}
