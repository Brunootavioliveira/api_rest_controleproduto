
package com.example.projeto.service;

import com.example.projeto.model.Product;
import com.example.projeto.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Transactional
    public Product create(Product p) {
        if (p.getPrice() < 0) throw new IllegalArgumentException("Price must be positive");
        return productRepository.save(p);
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product p = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        p.setName(updated.getName());
        p.setPrice(updated.getPrice());
        p.setStock(updated.getStock());
        return productRepository.save(p);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
