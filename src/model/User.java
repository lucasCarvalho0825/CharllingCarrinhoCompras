package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private static int ID = 1;

    private int id;
    private String nome;
    private String cpf;
    private String senha;

    private List<Cart> carrinho;

    public User(String nome, String cpf, String senha) {
        this.id = ID;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.carrinho = new ArrayList<>();

        ID++;
    }

    @Override
    public String toString() {
        return  " nome: " + nome + " cpf: " + cpf + " senha: " + senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Cart> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Cart carrinho) {
        this.carrinho.add(carrinho);
    }


}
