package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacher_id;

    @NotEmpty(message = "teacher first name should not be empty!")
    @Size(min = 2, max = 10,message = "teacher first name should be less than '10' and more than '2' chars!")
    @Pattern(regexp = "^[a-z A-Z]+$",message = "first name must contain only characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String fName;

    @NotEmpty(message = "teacher last name should not be empty!")
    @Size(min = 2, max = 10,message = "teacher last name should be less than '10' and more than '2' chars!")
    @Pattern(regexp = "^[a-z A-Z]+$",message = "last name must contain only characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String lName;

    @Email(message = "Email not valid please try another!")
    @NotEmpty(message = "Email cannot be empty!")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @NotNull(message = "teacher age should not be empty!")
    @Positive(message = "teacher age should be positive!")
    @Min(value = 25,message = "teacher age should be more than '25' !")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotEmpty(message = "teacher phone number should not be empty!")
    @Size(min = 10,max = 10,message = "teacher phone number should be '10' digits")
    @Pattern(regexp = "^05\\d*$",message = "Phone number must start with '05' !")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;


    @NotEmpty(message = "career cannot be empty!")
    @Size(max = 20,message = "career should be less than '20' chars!")
    @Column(columnDefinition = "varchar(20) not null")
    private String career;

    @NotEmpty(message = "city cannot be empty!")
    @Size(max = 25,message = "city should be less than '25' chars!")
    @Pattern(regexp = "^[a-z A-Z]+$",message = "city must contain only characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String city;


    @Column(columnDefinition = "double default 0")
    private double rating;

    @Column(columnDefinition = "double default 0")
    private double balance;
}
