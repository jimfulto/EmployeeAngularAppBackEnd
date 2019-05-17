package com.galvanize.employeedb.Controllers;

import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Services.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Api is used for Swagger Doc.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Api(value="Employee Controller", description="Operations to manipulate Employees data")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**Swagger doc
     * @ApiOperation and  @ApiResponses helps putting description in Swagger doc.
     * @return
     */
    @ApiOperation(value = "Get list of all Employees", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/all", produces = "application/json")
    public Iterable<Employee> all() {
        return employeeService.getAllEmployees();
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiOperation(value = "Add an employee", response = Iterable.class)
    @PostMapping(value = "/add", produces = "application/json")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ApiOperation(value = "Get Employee by ID", response = Iterable.class)
    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Employee> getById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

}
