package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if(posService.getCart() == null){
            return "No new cart has been created, unable to add products.";
        }
        if (amount <= 0) {
            return "Add failed.The amount of products should be more than 0.";
        }
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        else
            return "Add failed.Product \"" + productId + "\" could not be found.";
    }

    @ShellMethod(value = "View Cart", key = "v")
    public String viewCart() {
        if(posService.getCart() == null){
            return "No new cart has been created, unable to view cart.";
        }
        return posService.getCart().toString();
    }

    @ShellMethod(value = "Empty Cart", key = "e")
    public String emptyCart() {
        if(posService.getCart() == null){
            return "No new cart has been created, unable to empty cart.";
        }
        posService.getCart().empty();
        return "The cart has been emptied.";
    }

    @ShellMethod(value = "Modify Product Amount in Cart", key = "m")
    public String modifyCart(String productId, int amount) {
        if(posService.getCart() == null){
            return "No new cart has been created, unable to add products.";
        }
        if (amount <= 0) {
            return "Modify failed.The amount of products should be more than 0.";
        }
        if (posService.modify(productId, amount)) {
            return posService.getCart().toString();
        }
        return "Modify failed.Product \"" + productId + "\" could not be found.";
    }

    @ShellMethod(value = "Check Out Cart", key = "c")
    public String checkOutCart() {
        if(posService.getCart() == null){
            return "No new cart has been created, unable to check out cart.";
        }
        double sum = posService.total(posService.getCart());
        posService.checkout(posService.getCart());
        return "Total: " + sum + "\nCheck Out Successfully.\nCart is empty now.";
    }
}
