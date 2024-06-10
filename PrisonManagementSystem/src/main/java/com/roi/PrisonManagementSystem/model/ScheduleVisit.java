package com.roi.PrisonManagementSystem.model;

import com.roi.PrisonManagementSystem.model.enums.StatusVisitEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedule_visit")
@Data
public class ScheduleVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "detainee_id", nullable = false)
    private Detainee detainee;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @Column(nullable = false)
    private LocalDateTime visitDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusVisitEnum status;

    @Column(nullable = false, length = 300)
    private String description;
}
