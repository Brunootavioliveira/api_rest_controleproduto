
package com.example.projeto.controller;

import com.example.projeto.model.Product;
import com.example.projeto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listar() {
        return productService.listarTodos();
    }

    @GetMapping("/{id}")
    public Product buscar(@PathVariable Long id) {
        return productService.buscarPorId(id);
    }

    @PostMapping
    public Product criar(@RequestBody Product produto) {
        return productService.salvar(produto);
    }

    @PutMapping("/{id}")
    public Product atualizar(@PathVariable Long id, @RequestBody Product product) {
        return productService.atualizar(id, product);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        productService.deletar(id);
    }
}
