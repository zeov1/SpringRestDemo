package ru.zeovl.springrestdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.services.EmployeeService;
import ru.zeovl.springrestdemo.utils.EmployeeErrorResponse;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundException;

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
    Employee getEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return employeeService.findById(id);
    }

    @ExceptionHandler
    ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException e) {
        EmployeeErrorResponse r = new EmployeeErrorResponse(
                "Employee " + e.getRequestedEmployeeId() + " not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<EmployeeErrorResponse>(r, HttpStatus.NOT_FOUND);
    }
}
