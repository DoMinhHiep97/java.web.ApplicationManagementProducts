package vn.dominhhiep.service;

import vn.dominhhiep.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private static Map<Integer, Product> productMap;
//hash map gom case, value.. search lay ra value roi duyet value.
    static {
        productMap = new HashMap<>();
        productMap.put(1, new Product(1, "Iphone 5", 500, "Smartphone", "Iphone"));
        productMap.put(2, new Product(2, "Iphone 6", 600, "Smartphone", "Iphone"));
        productMap.put(3, new Product(3, "Iphone 7", 700, "Smartphone", "Iphone"));
        productMap.put(4, new Product(4, "NoKia 1080", 200, "Classic Phone", "NoKia"));
        productMap.put(5, new Product(5, "SamSung Galaxy M20", 1100, "Smartphone", "SamSung"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public void save(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return productMap.get(id);
    }

    @Override
    public void update(int id, Product product) {
        productMap.put(id, product);
    }

    @Override
    public void remove(int id) {
        productMap.remove(id);
    }

}
