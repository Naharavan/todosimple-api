package com.lucasangelo.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;
import com.lucasangelo.todosimple.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> 
            new RuntimeException("Usuário não encontrado! ID: " + id + ", Tipo: " + User.class.getName())
        );
    }

    @org.springframework.transaction.annotation.Transactional
    public User create(User obj) {
        obj.setId(null); // Define o ID como null para garantir que o registro seja inserido como um novo
        obj = this.userRepository.save(obj); // Salva o usuário e atualiza a instância com o ID gerado
        this.taskRepository.saveAll(obj.getTasks()); // Salva todas as tarefas associadas ao usuário
        return obj; // Retorna o objeto User salvo
    }

    @org.springframework.transaction.annotation.Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        try {
            findById(id); // Verifica se o usuário existe
            this.userRepository.deleteById(id); // Exclui o usuário pelo ID
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!", e);
        }
    }
    

}
