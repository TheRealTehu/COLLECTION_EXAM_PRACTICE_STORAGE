package org.example;

import java.time.LocalDate;
import java.util.Set;

public class Product {
    private String name;
    private int price;
    private LocalDate expirationDate;
    private Set<String> categories;

    public Product(String name, int price, LocalDate expirationDate, Set<String> categories) {
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public boolean isExpired(){
        return expirationDate.isBefore(LocalDate.now());
    }
}
