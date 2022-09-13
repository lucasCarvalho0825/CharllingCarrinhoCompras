package model;

public class Item {
    private static Integer ID = 1;

    private Integer id;
    private Product product;
    private Integer qtd;

    public Item(Product product, Integer qtd) {
        this.id = ID;
        this.product = product;
        this.qtd = qtd;

        this.ID++;

    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = this.qtd - qtd;
    }

    @Override
    public String toString() {
        String dispinivel = (qtd > 0) ? " disponiveis: " + qtd : " Item indisponivel";
        return product.toString() + dispinivel;
    }
}
