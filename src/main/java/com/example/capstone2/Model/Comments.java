package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @NotNull(message = "Application comment cannot be empty!")
    @Column(columnDefinition = "int not null")
    private int application_id;

    @NotNull(message = "Student id cannot be empty!")
    @Column(columnDefinition = "int not null")
    private int student_id;

    @Column
    private String student_name;

    @NotEmpty(message = "comment cannot be empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String comment;
}
