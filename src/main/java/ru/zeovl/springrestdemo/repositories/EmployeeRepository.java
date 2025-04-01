package ru.zeovl.springrestdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.zeovl.springrestdemo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
