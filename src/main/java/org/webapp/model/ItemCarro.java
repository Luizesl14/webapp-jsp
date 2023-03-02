package org.webapp.model;

import java.util.Objects;

public class ItemCarro {

    private int quantity;
    private Produto produto;

    public ItemCarro(int quantity, Produto produto) {
        this.quantity = quantity;
        this.produto = produto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int  getTolal(){
        return quantity * produto.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return Objects.equals(produto.getId(), itemCarro.produto.getId())
                && Objects.equals(produto.getName(), itemCarro.produto.getName());
    }
}
