package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Storage {
    private List<Product> products;

    public Storage(List<Product> products) {
        if(products == null){
            throw new IllegalArgumentException();
        }
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> findProductsOnlyValidToday(){
        return products.stream().filter(p -> p.getExpirationDate().equals(LocalDate.now())).collect(Collectors.toList());
    }

    public Set<String> getProductNamesInCategory(String category){
        return products.stream()
                .filter(p -> p.getCategories().contains(category))
                .map(Product::getName)
                .collect(Collectors.toSet());
    }

    public int calculateMoneylossOfExpiredProducts(){
        return products.stream().filter(Product::isExpired).mapToInt(Product::getPrice).sum();
    }

    public List<String> listCategoriesOrderedByMoneylossDesc(){
        Map<String, Integer> categoryLoss = new HashMap<>();

        for (Product product : products) {
            for (String category : product.getCategories()) {
                if(!categoryLoss.containsKey(category)){
                    categoryLoss.put(category, product.isExpired() ? product.getPrice() : 0);
                } else {
                    if (product.isExpired()){
                        categoryLoss.put(category, categoryLoss.get(category) + product.getPrice());
                    }
                }
            }
        }

        return categoryLoss.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void removeAllExpiredProducts(){
        products.removeIf(Product::isExpired);
    }
}
