package com.lucasangelo.todosimple.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User { // Nome da classe corrigido para User
    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Corrigido para WRITE_ONLY
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;

    // private List<Task> task = new ArrayList<Task>();

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) // Verifica se o objeto comparado é o próprio objeto
            return true;
        if (obj == null) // Verifica se o objeto comparado é nulo
            return false;
        if (!(obj instanceof User)) // Verifica se o objeto comparado é uma instância de User
            return false;
        
            User other = (User) obj;

            // Verifica se o campo `id` não é nulo e compara os IDs
            if (this.id == null) {
                if (other.getId() != null) 
                    return false;
            } else if (!this.id.equals(other.getId())) { 
                return false;
            }
    
            // Compara `username` e `password`
            return Objects.equals(this.id, other.getId())
                &&Objects.equals(this.username, other.getUsername())
                && Objects.equals(this.password, other.getPassword());
        }
    
        @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : id.hashCode());
        return result;
    }
    }