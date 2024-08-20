package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    @NotEmpty(message = "student first name should not be empty!")
    @Size(min = 2, max = 15,message = "student first name should be less than '10' and more than '2' chars!")
    @Pattern(regexp = "^[a-z A-Z]+$",message = "first name must contain only characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String f_Name;

    @NotEmpty(message = "student last name should not be empty!")
    @Size(min = 2, max = 15,message = "student last name should be less than '10' and more than '2' chars!")
    @Pattern(regexp = "^[a-z A-Z]+$",message = "last name must contain only characters")
    @Column(columnDefinition = "varchar(10) not null")
    private String l_Name;

    @Email(message = "Email not valid please try another!")
    @NotEmpty(message = "Email cannot be empty!")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @NotNull(message = "student age should not be empty!")
    @Positive(message = "student age should be positive!")
    @Min(value = 6,message = "student age should be more than '6' !")
    @Column(columnDefinition = "int not null")
    private int age;

    @NotEmpty(message = "student phone number should not be empty!")
    @Size(min = 10,max = 10,message = "student phone number should be '10' digits")
    @Pattern(regexp = "^05\\d*$",message = "Phone number must start with '05' !")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;

    @NotEmpty(message = "education level should not be empty!")
    @Pattern(regexp = "^(Primary|Middle|High|Diploma|Bachelors)$",message = "Eduction level must be 'Primary OR Middle OR High OR Diploma OR Bachelors'.")
    @Column(columnDefinition = "varchar(11) not null")
    private String EducationLevel;

    @NotEmpty(message = "city cannot be empty!")
    @Size(max = 25,message = "city should be less than '25' chars!")
    @Pattern(regexp = "^[a-z A-Z]+$",message = "city must contain only characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String city;

}
