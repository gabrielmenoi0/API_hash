package com.example.encurtadorurl.encurtador.url.DTO;

public class ReciveClienteDTO {
    private String nome;
    private String senha;

    public ReciveClienteDTO(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
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
