package model;

import java.util.List;
import java.util.Map;

public class Cart {
    private static int ID = 1;

    private int id;
    private List<Product> lista;
    private double valorCompra;

    public Cart(List<Product> lista, double valorCompra) {
        this.id = ID;
        this.lista = lista;
        this.valorCompra = valorCompra;

        ID++;
    }

    public List<Product> getLista() {
        return lista;
    }

    public void setLista(List<Product> lista) {
        this.lista = lista;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public String toString() {

        String itens = "";
        for (Product p : lista){
            itens += p.toString() + "\n";
        }

        return  " Cart " + id
                + "- Products list : \n"
                + itens
                + "valorCompra=" + valorCompra
                + "\n";
    }
}
