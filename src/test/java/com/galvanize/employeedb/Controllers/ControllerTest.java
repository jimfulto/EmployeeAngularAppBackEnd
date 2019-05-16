package com.galvanize.employeedb.Controllers;


import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Services.EmployeeService;
import com.galvanize.employeedb.Services.JobService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @InjectMocks
    EmployeeController employeeController;

    @InjectMocks
    JobController jobController;

    @Mock
    EmployeeService employeeService;

    @Mock
    JobService jobService;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }


    @Test
    public void getEmployeeList() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Employee employee = new Employee(1L, "Joe Test", "123 Any Street", "Anytown", "GA", "30269", sdf.parse("03/14/2018"));

//        when(employeeService.getAllEmployees()).thenReturn(Collections.singleton(employee));

        Iterable<Employee> result = employeeController.all();

        result.forEach(e -> System.out.println(e.getName()));


        verify(employeeService, times(0)).getAllEmployees();

    }


    @Test
    public void addToMovieList() throws Exception {
        mockMvc.perform(get("/employee/all")).andExpect(status().isOk());
    }


}
