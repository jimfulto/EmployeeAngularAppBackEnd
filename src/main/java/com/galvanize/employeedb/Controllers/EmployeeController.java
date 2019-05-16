package com.galvanize.employeedb.Controllers;

import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public Iterable<Employee> all() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/add")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

}
