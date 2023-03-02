package org.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public void  addItemCarro(ItemCarro itemCarro){
        if(items.contains(itemCarro)){
           Optional<ItemCarro> optionalItemCarro = items.stream()
                   .filter(itm-> itm.equals(itemCarro))
                   .findAny();
           if(optionalItemCarro.isPresent()){
               ItemCarro i = optionalItemCarro.get();
               i.setQuantity(i.getQuantity()+1);
           }
        }else {
            this.items.add(itemCarro);
        }

    }

    public List<ItemCarro> getItems(){
        return items;
    }

    public int getTotal(){
        return this.items.stream().mapToInt(ItemCarro::getTolal).sum();
    }
}

