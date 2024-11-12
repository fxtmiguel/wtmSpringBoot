package com.wtm.spring_boot_wtm.model;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "username", unique = true)
    private String username;

    public String getUsername() {
        return username;
    }

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", unique = true)
    private String password;

    @Column(name = "age")
    private Integer age;

    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}
