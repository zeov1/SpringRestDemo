package ru.zeovl.springrestdemo.services;

import org.springframework.stereotype.Service;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.repositories.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
