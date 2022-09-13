package model;

import utils.Utils;

public class Product {
    private static int ID = 1;

    private int id;
    private String nome;
    private double valor;

    public Product(String nome, double valor) {
        this.id = ID;
        this.nome = nome;
        this.valor = valor;

        ID++;
    }

    @Override
    public String toString() {
        return  id + "- " + " " + nome + " " + Utils.doubleToString(valor);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}