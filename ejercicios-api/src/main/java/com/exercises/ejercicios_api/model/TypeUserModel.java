package com.exercises.ejercicios_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "type_user")
@Data
public class TypeUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String name_type;
}
