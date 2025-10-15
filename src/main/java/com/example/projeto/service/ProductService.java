
package com.example.projeto.service;

import com.example.projeto.exception.ResourceNotFoundException;
import com.example.projeto.model.Product;
import com.example.projeto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository produtoRepository;

    public List<Product> listarTodos() {
        return produtoRepository.findAll();
    }

    public Product buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
    }

    public Product salvar(Product produto) {
        return produtoRepository.save(produto);
    }

    public Product atualizar(Long id, Product produtoAtualizado) {
        Product produto = buscarPorId(id);
        produto.setName(produtoAtualizado.getName());
        produto.setPrice(produtoAtualizado.getPrice());
        produto.setStock(produtoAtualizado.getStock());
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        Product product = buscarPorId(id);
        produtoRepository.delete(product);
    }
}