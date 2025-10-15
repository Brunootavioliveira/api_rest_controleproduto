
package com.example.projeto.service;

import com.example.projeto.exception.ResourceNotFoundException;
import com.example.projeto.model.User;
import com.example.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public User buscarPorId(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
    }

    public User salvar(User user) {
        return userRepository.save(user);
    }

    public User atualizar(Long id, User userAtualizado) {
        User usuario = buscarPorId(id);
        usuario.setName(userAtualizado.getName());
        usuario.setEmail(userAtualizado.getEmail());
        usuario.setPassword(userAtualizado.getPassword());
        return userRepository.save(usuario);
    }

    public void deletar(Long id) {
        User user = buscarPorId(id);
        userRepository.delete(user);
    }
}
