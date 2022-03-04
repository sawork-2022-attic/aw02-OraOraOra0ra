package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {
        for (Item temp: items) {
            if (temp.getProduct() == item.getProduct()) {
                   temp.addAmount(item.getAmount());
                   return true;
            }
        }
        return items.add(item);
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }

    public void empty() {
        items.clear();
    }

    public boolean modify(Product product, int amount) {
        for (Item temp: items) {
            if (temp.getProduct() == product) {
                temp.modify(amount);
                return true;
            }
        }
        return false;
    }

    public double total() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        return total;
    }
}
