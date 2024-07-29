package com.java_app.app.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(name="username", nullable = false, unique=true)
    private String username;
   
    @Column(name="email", nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
   
   
    @ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)  // Specifies a many-to-many relationship with eager fetching and cascading all operations
    
    @JoinTable(name="users_roles",
    joinColumns= @JoinColumn(name="user_id", referencedColumnName="id")  // Joins the "user_id" column with the "id" column in the "users" table
    ,inverseJoinColumns=@JoinColumn(name="role_id", referencedColumnName="id"))  // Joins the "role_id" column with the "id" column in the "roles" table
    
    private Set<Role> roles ;


}
