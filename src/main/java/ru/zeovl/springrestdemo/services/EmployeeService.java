package ru.zeovl.springrestdemo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.zeovl.springrestdemo.models.Employee;
import ru.zeovl.springrestdemo.repositories.EmployeeRepository;
import ru.zeovl.springrestdemo.utils.EmployeeNotFoundException;
import ru.zeovl.springrestdemo.utils.InvalidEmployeeException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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
        return employee.orElseThrow(
                () -> new EmployeeNotFoundException(id)
        );
    }

    @Transactional
    public Employee save(Employee employee, BindingResult bindingResult) throws InvalidEmployeeException {
        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                message
                        .append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new InvalidEmployeeException(message.toString());
        }

        employee.setCreatedAt(LocalDate.now());
        employee.setCreatedBy("UNKNOWN"); // TODO необходимо создать пользователей

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(int id, Employee employee, BindingResult bindingResult) throws InvalidEmployeeException {

        Employee employeeToBeUpdated = employeeRepository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id)
        );

        if (bindingResult.hasErrors()) {
            StringBuilder message = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                message
                        .append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new InvalidEmployeeException(message.toString());
        }

        employeeToBeUpdated.setFirstName(employee.getFirstName());
        employeeToBeUpdated.setLastName(employee.getLastName());
        employeeToBeUpdated.setDateOfBirth(employee.getDateOfBirth());
        employeeToBeUpdated.setAddress(employee.getAddress());
        employeeToBeUpdated.setEducation(employee.getEducation());
        employeeToBeUpdated.setDateHired(employee.getDateHired());

        return employeeRepository.save(employeeToBeUpdated);
    }

    @Transactional
    public void delete(int id) throws EmployeeNotFoundException {
        if (!employeeRepository.existsById(id))
            throw new EmployeeNotFoundException(id);

        employeeRepository.deleteById(id);
    }
}
