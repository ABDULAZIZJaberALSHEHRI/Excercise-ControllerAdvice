package com.example.capstone2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer financialReturn_Id;

    @Column(columnDefinition = "int")
    private int student_request_id;

    @Column(columnDefinition = "int")
    private int teacher_id;

    @Column(columnDefinition = "varchar(20) default 'bending'")
    private String process_status="bending";

    @Column(columnDefinition = "double")
    private double cost;

    @Column(columnDefinition = "double default 0")
    private double financialReturn;
}
