package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher_Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String historical_process="Student Application Request";

    @Column(columnDefinition = "int")
    private int teacher_id;

    @Column(columnDefinition = "int")
    private int student_Request_id;

    @Column(columnDefinition = "double default 0")
    private double process_value;
    @Column(columnDefinition = "double default 0")
    private double process_value_after_percentage;
    @Column(columnDefinition = "double default 0")
    private double total_balance;
}
