package ru.zeovl.springrestdemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "education")
    private String education;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "date_hired")
    private LocalDate dateHired;
}
