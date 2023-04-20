package com.example.encurtadorurl.encurtador.url.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_cliente")
public class cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String DateRegister;
    private String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateRegister() {
        return DateRegister;
    }



    public String getDateRegister(String s) {
        return DateRegister;
    }

    public cliente(UUID id, String nome, String senha, String dateRegister, String email, String token) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.Token = token;
        DateRegister = dateRegister;
    }

    public void setDateRegister(String dateRegister) {
        DateRegister = dateRegister;
    }


    public cliente() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
