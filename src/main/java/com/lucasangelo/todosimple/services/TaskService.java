package com.lucasangelo.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;
    
    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> 
            new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName())
        );
    }
    
    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId()); // Busca o usuário associado
        obj.setId(null); // Define o ID como null para garantir que o Task será inserido como novo
        obj.setUser(user); // Define o usuário associado ao Task
        obj = this.taskRepository.save(obj); // Salva o Task no repositório
        return obj; // Retorna o Task salvo
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!", e);
        }
    }

    
}
