package com.roi.PrisonManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "visitor")
@Data
public class Visitor {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false, length = 50)
    private String nameVisitor;

    @Column(nullable = false, unique = true, length = 50)
    private String idCardVisitor;

    @Column(nullable = false, unique = true, length = 50)
    private String phoneVisitor;
}
