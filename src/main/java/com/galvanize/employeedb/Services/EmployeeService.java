package com.galvanize.employeedb.Services;

import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Entities.Job;
import com.galvanize.employeedb.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public EmployeeService() {
    }

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }


    public Iterable<Employee> getAllEmployees() {
        return this.repository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return this.repository.save(employee);
    }

    public Optional<Employee> getEmployeeById(long id) {
        return this.repository.findById(id);
    }

}
