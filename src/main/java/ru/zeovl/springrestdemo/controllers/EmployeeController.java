package ru.zeovl.springrestdemo.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zeovl.springrestdemo.dto.EmployeeDTO;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.services.EmployeeService;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundException;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundResponse;
import ru.zeovl.springrestdemo.utils.InvalidEmployeeException;
import ru.zeovl.springrestdemo.utils.InvalidEmployeeResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    // CREATE

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(
            @RequestBody @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) throws InvalidEmployeeException {

        Employee employee = convertToEmployee(employeeDTO);
        Employee savedEmployee = employeeService.save(employee, bindingResult);
        URI location = URI.create("/employee/" + savedEmployee.getId());
        EmployeeDTO savedEmployeeDTO = convertToDTO(savedEmployee);
        return ResponseEntity.created(location).body(savedEmployeeDTO);
    }

    // READ

    @GetMapping("")
    List<EmployeeDTO> getEmployees() {
        return employeeService.findAll().stream().map(this::convertToDTO).toList();
    }

    @GetMapping("/{id}")
    EmployeeDTO getEmployee(@PathVariable("id") int id) throws EmployeeNotFoundException {
        return convertToDTO(employeeService.findById(id));
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

    // DTO converting

    private EmployeeDTO convertToDTO(Employee employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }
}
