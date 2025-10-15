
package com.example.projeto.controller;

import com.example.projeto.model.User;
import com.example.projeto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listar() {
        return userService.listarTodos();
    }

    @GetMapping("/{id}")
    public User buscar(@PathVariable Long id) {
        return userService.buscarPorId(id);
    }

    @PostMapping
    public User criar(@RequestBody User user) {
        return userService.salvar(user);
    }

    @PutMapping("/{id}")
    public User atualizar(@PathVariable Long id, @RequestBody User user) {
        return userService.atualizar(id, user);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        userService.deletar(id);
    }
}
