package Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.employeedb.Controllers.EmployeeController;
import com.galvanize.employeedb.Controllers.JobController;
import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Entities.Job;
import com.galvanize.employeedb.Repositories.JobRepository;
import com.galvanize.employeedb.Services.EmployeeService;
import com.galvanize.employeedb.Repositories.EmployeeRepository;
import com.galvanize.employeedb.Services.JobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class   )
@SpringBootTest
public class EmployeeTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private JobRepository jobRepository;

    @Test
    public void getEmployee() {
        //Setup
        given(employeeRepository.findAll()).willReturn(new Iterable<Employee>() {
            @Override
            public Iterator<Employee> iterator() {
                return null;
            }
        });
        EmployeeService es = new EmployeeService(employeeRepository);
        EmployeeController sut = new EmployeeController(es);
        //Execute
        Iterable<Employee> employees = sut.all();

        //Assert
        then(employeeRepository).should(times(1)).findAll();

        //Teardown
    }

    @Test
    public void getEmplloyeeShouldReturn() throws ParseException{
        //Setup
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Employee employee = new Employee(1L, "Joe Test", "123 Any Street", "Anytown", "GA", "30269", sdf.parse("03/14/2018"));

        given(employeeRepository.findById(1L)).willReturn(java.util.Optional.of(employee));
        EmployeeService es = new EmployeeService(employeeRepository);
        EmployeeController sut = new EmployeeController(es);

        //Execute
        Optional<Employee> employeeActual = sut.getById(1L);

        //Assert
        then(employeeRepository).should(times(1)).findById(1L);

        //Teardown
    }

    @Test
    public void postEmployeeShouldAdd() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Employee employee = new Employee(1L, "Joe Test", "123 Any Street", "Anytown", "GA", "30269", sdf.parse("03/14/2018"));
        //Setup
        given(employeeRepository.save( employee )).willReturn( employee );
        EmployeeService es = new EmployeeService(employeeRepository);
        EmployeeController sut = new EmployeeController(es);

        //Execute
        Employee employees = sut.save(employee);


        //Assert
        then(employeeRepository).should(times(1)).save(employee);


        //Teardown
    }


    @Test
    public void postJobShouldAdd() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Job job = new Job(1L, 1L, "Customer", sdf.parse("05/08/2019"), sdf.parse("05/09/2019"));
        //setup
        given(jobRepository.save(job)).willReturn(job);
        JobService js = new JobService(jobRepository);
        JobController sut = new JobController(js);

        //Execute
        Job jobs = sut.save(job);

        //Assert
        then(jobRepository).should(times(1)).save(job);
    }


    @Test
    public void putJobShould() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Job job = new Job(1L, 1L, "Customer", sdf.parse("05/08/2019"), sdf.parse("05/09/2019"));
        //setup
        given(jobRepository.findById(1L)).willReturn(java.util.Optional.of(job));
        given(jobRepository.save(job)).willReturn(job);
        JobService js = new JobService(jobRepository);
        JobController sut = new JobController(js);

        //Execute
        ModelMap mm = new ModelMap();
        mm.addAttribute("jobID", 1L);
        mm.addAttribute("customer", "Cust2");
        Job actual = sut.updateCustomer(1L, mm);

        //Assert
        then(jobRepository).should(times(1)).findById(1L);
        then(jobRepository).should(times(1)).save(job);
        then(actual.getCustomer().equals("Cust2"));

    }
}