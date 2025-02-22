package com.java_app.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role {
   
   
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  // Specifies the primary key generation strategy
    private Long id;  // ID of the role
    private String name;  // Name of the role

    
}
