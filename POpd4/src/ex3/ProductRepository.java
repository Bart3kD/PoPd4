package ex3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {

    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> sortProductsByName() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    public List<Product> sortProductsByPriceDescending() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    public boolean areAllProductsExpensive(double threshold) {
        return products.stream()
                .allMatch(product -> product.getPrice() > threshold);
    }

    public boolean isAnyProductCheap(double threshold) {
        return products.stream()
                .anyMatch(product -> product.getPrice() < threshold);
    }

    public List<Product> findProductsByNameContaining(String name) {
        String lowerCaseName = name.toLowerCase();
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());
    }
}
