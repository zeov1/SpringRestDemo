package ru.zeovl.springrestdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable("id") int id) {
        return employeeService.findById(id);
    }
}
