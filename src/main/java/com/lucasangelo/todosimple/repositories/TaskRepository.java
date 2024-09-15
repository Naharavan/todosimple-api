package com.lucasangelo.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_Id(Long id); //Neste não pode mudar o nome findByUser_Id

//@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
// List<Task> findByUser_Id(@Param("id") Long id); //Neste pode mudar o nome findByUser_Id ou xyz...
    
//@Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
// List<Task> findByUser_Id(@Param("id") Long id);

}
