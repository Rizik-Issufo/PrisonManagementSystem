package com.roi.PrisonManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "detainee")
@Data
public class Detainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String surname;

    @Column(nullable = false, unique = true, length = 20)
    private String idCard;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 10)
    private float height;

    @Column(nullable = false, length = 10)
    private float weight;

    @Column(nullable = false)
    private LocalDateTime prisonDate;

    @Column(nullable = false)
    private String photo1;

    @Column
    private String photo2;

    @OneToMany(mappedBy = "detainee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Crime> crimes;

    @ManyToOne
    @JoinColumn(name = "cell_prison_id", nullable = false)
    private CellPrison cellPrison;

    @Column(length = 300)
    private String description;
}
