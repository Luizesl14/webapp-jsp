package org.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    List<ItemCarro> itens;

    public Carro() {
        this.itens = new ArrayList<>();
    }

    public void  addItemCarro(ItemCarro itemCarro){
        if(itens.contains(itemCarro)){
           Optional<ItemCarro> optionalItemCarro = itens.stream()
                   .filter(itm-> itm.equals(itemCarro))
                   .findAny();
           if(optionalItemCarro.isPresent()){
               ItemCarro i = optionalItemCarro.get();
               i.setQuantity(i.getQuantity()+1);
           }
        }else {
            this.itens.add(itemCarro);
        }
    }

    public void removeProducts(List<String> productsIds){
        itens.forEach(System.out::println);
        for (ItemCarro itemCarro: this.itens) {
            if(productsIds != null && productsIds.isEmpty()){
                this.itens.remove(itemCarro);
            }
        }
    }

    public List<ItemCarro> getItens(){
        return itens;
    }

    public int getTotal(){
        return this.itens.stream().mapToInt(ItemCarro::getTolal).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            //productoIds.forEach(this::removeProducto);
            productoIds.forEach(this::removeProducto);
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itens.remove(itemCarro));
    }

    public void updateQuantity(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setQuantity(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  itens.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProduto().getId())))
                .findAny();
    }

}

