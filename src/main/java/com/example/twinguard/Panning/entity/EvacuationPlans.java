package com.example.twinguard.Panning.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "evacuation_plans")
public class EvacuationPlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "time", nullable = false, length = 50)
    private String time;

    @Column(name = "description", nullable = false, length = 255)
    private String description;
}
