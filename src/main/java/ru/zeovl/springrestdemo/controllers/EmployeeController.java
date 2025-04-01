package ru.zeovl.springrestdemo.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.services.EmployeeService;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundResponse;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundException;
import ru.zeovl.springrestdemo.utils.InvalidEmployeeResponse;
import ru.zeovl.springrestdemo.utils.InvalidEmployeeException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // CREATE

    @PostMapping("")
    public ResponseEntity<Employee> createEmployee(
            @RequestBody @Valid Employee employee, BindingResult bindingResult) throws InvalidEmployeeException {
        Employee savedEmployee = employeeService.save(employee, bindingResult);
        URI location = URI.create("/employee/" + savedEmployee.getId());
        return ResponseEntity.created(location).body(savedEmployee);
    }

    // READ

    @GetMapping("")
    List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return employeeService.findById(id);
    }

    // UPDATE

    // DELETE

    // Exceptions handling

    @ExceptionHandler
    ResponseEntity<InvalidEmployeeResponse> handleException(InvalidEmployeeException e) {
        InvalidEmployeeResponse r = new InvalidEmployeeResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return ResponseEntity.badRequest().body(r);
    }

    @ExceptionHandler
    ResponseEntity<EmployeeNotFoundResponse> handleException(EmployeeNotFoundException e) {
        EmployeeNotFoundResponse r = new EmployeeNotFoundResponse(
                "Employee " + e.getRequestedEmployeeId() + " not found!",
                System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(r);
    }
}
