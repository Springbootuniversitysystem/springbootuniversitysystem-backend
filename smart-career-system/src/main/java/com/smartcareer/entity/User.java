package com.smartcareer.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    public  String email;

    @Column(nullable = false)
    public  String password;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private boolean active= true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    //put the roles in a list
    private List<Role> roles = new ArrayList<>();


    @PrePersist //set automatically on first save

    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
