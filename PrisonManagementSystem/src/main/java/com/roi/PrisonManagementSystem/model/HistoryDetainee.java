package com.roi.PrisonManagementSystem.model;

import com.roi.PrisonManagementSystem.model.enums.PenaltyEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "history_detainee")
@Data
public class HistoryDetainee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false)
    private int numberCell;

    @ManyToOne
    @JoinColumn(name = "detainee_id", nullable = false)
    private Detainee detainee;

    @Column(nullable = false)
    private LocalDate entryDate;

    @Column(nullable = false)
    private double durationPenalty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PenaltyEnum penaltyType;

    @Column(nullable = false, length = 200)
    private String prisionLocal;

    @Column(nullable = false, length = 200)
    private String crimeType;

    @Column(length = 500)
    private String details;
}
