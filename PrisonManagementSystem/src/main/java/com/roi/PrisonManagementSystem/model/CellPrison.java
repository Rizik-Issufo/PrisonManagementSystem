package com.roi.PrisonManagementSystem.model;

import com.roi.PrisonManagementSystem.model.enums.CategoryCellEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cell_prison")
@Data
public class CellPrison implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private int numberCell;

    @Column(nullable = false)
    private byte floor;

    @Column(nullable = false)
    private int capacity;

    @OneToMany(mappedBy = "cellPrison", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Detainee> detainees;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoryCellEnum categoryCell;
}
