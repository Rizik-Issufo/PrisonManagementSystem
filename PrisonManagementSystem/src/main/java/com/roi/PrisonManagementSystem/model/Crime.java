package com.roi.PrisonManagementSystem.model;

import com.roi.PrisonManagementSystem.model.enums.PenaltyEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "crime")
@Data
public class Crime implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(nullable = false, unique = true)
    private String crimeType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double durationPenalty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PenaltyEnum penalty;

    @Column(length = 500)
    private String details;

    @ManyToOne
    @JoinColumn(name = "detainee_id", nullable = false)
    private Detainee detainee;
}
