package com.example.capstone2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Published_Applications {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int published_Applications_id;

    @Column(columnDefinition = "int")
    private int teacher_id;

    @Column(columnDefinition = "int")
    private int application_id;

    @Column(columnDefinition = "varchar(30)")
    private String application_title;

}
