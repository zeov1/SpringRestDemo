package ru.zeovl.springrestdemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;


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

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, LocalDate dateOfBirth, String education, String address, LocalDate dateHired) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.address = address;
        this.dateHired = dateHired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotNull @Size(min = 2, max = 100) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull @Size(min = 2, max = 100) String firstName) {
        this.firstName = firstName;
    }

    public @NotNull @Size(min = 2, max = 100) String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull @Size(min = 2, max = 100) String lastName) {
        this.lastName = lastName;
    }

    public @NotNull LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotNull LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotNull String getEducation() {
        return education;
    }

    public void setEducation(@NotNull String education) {
        this.education = education;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    public @NotNull LocalDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(@NotNull LocalDate dateHired) {
        this.dateHired = dateHired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(dateOfBirth, employee.dateOfBirth) && Objects.equals(education, employee.education) && Objects.equals(address, employee.address) && Objects.equals(dateHired, employee.dateHired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, education, address, dateHired);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", education='" + education + '\'' +
                ", address='" + address + '\'' +
                ", dateHired=" + dateHired +
                '}';
    }
}
