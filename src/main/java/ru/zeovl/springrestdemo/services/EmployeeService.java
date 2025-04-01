package ru.zeovl.springrestdemo.services;

import org.springframework.stereotype.Service;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.repositories.EmployeeRepository;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
