package org.example.Models;

import org.dizitart.no2.objects.Id;

public class Item {
    @Id
    private String name;
    private String size;
    private String price;

    public Item(String name,String size,String price){
        this.name=name;
        this.size=size;
        this.price=price;
    }

    public Item(){
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
