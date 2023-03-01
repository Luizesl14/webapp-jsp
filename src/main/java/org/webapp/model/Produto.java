package org.webapp.model;

public class Produto {
    private Long id;
    private String name;
    private String tipo;
    private int price;

    public Produto() {
    }

    public Produto(Long id, String name, String tipo, int price) {
        this.id = id;
        this.name = name;
        this.tipo = tipo;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
