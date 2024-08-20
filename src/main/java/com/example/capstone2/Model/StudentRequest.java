package com.example.capstone2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_Request_id;

    @Column(columnDefinition = "int")
    private int application_id;

    @Column(columnDefinition = "int")
    private int student_id;

    @Column(columnDefinition = "varchar(15)")
    private String student_name;

    @Column(columnDefinition = "varchar(20) default 'bending'")
    private String request_status="bending";
}
