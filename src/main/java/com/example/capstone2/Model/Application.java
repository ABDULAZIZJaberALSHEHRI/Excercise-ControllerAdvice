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
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer application_id;

    @NotEmpty(message = "Application title should not be empty!")
    @Size(max = 30,message = "Application title should be less than 30 chars!")
    @Column(columnDefinition = "varchar(30) not null")
    private String application_Title;

    @NotEmpty(message = "Application description should not be empty!")
    @Size(max = 100,message = "Application description should be less than 100 chars!")
    @Column(columnDefinition = "varchar(100) not null")
    private String application_Description;

    @NotNull(message = "Teacher id should not be empty!")
    @Positive(message = "Teacher id should be positive number!")
    @Column(columnDefinition = "int not null")
    private Integer teacher_id;


    @NotEmpty(message = "Target student should not be empty!")
    @Pattern(regexp = "^(Primary|Middle|High|Diploma|Bachelors)$",message = "Target student must be 'Primary OR Middle OR High OR Diploma OR Bachelors'.")
    @Column(columnDefinition = "varchar(11) not null")
    private String target_Student;

    @NotEmpty(message = "Course name should not be empty!")
    @Size(max = 20,message = "Course name should be less than 20 chars!")
    @Column(columnDefinition = "varchar(20) not null")
    private String course_Name;

    @NotEmpty(message = "Course duration should not be empty!")
    @Size(max = 15,message = "Course duration should be less than 15 chars!")
    @Column(columnDefinition = "varchar(15) not null")
    private String course_Duration;

    @NotEmpty(message = "Session type should not be empty!")
    @Pattern(regexp = "^(online|in class room|recorded)$",message = "session type must match 'online OR in class room OR recorded'.")
    @Column(columnDefinition = "varchar(15) not null")
    private String session_Type;

    @NotEmpty(message = "Session location should not be empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String session_Location;

    @NotNull(message = "Start date should not be empty!")
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(columnDefinition = "date not null")
    private Date start_date;

    @NotNull(message = "End date should not be empty!")
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(columnDefinition = "date not null")
    private Date end_date;

    @NotNull(message = "Start time should not be empty!")
    @JsonFormat(pattern = "hh:mm:ss")
    @Column(columnDefinition = "time not null")
    private Time start_time;

    @NotNull(message = "End time should not be empty!")
    @JsonFormat(pattern = "hh:mm:ss")
    @Column(columnDefinition = "time not null")
    private Time end_time;

    @NotNull(message = "Cost should not be empty!")
    @Positive(message = "Cost should be positive number!")
    @Column(columnDefinition = "double not null")
    private double cost;

}
