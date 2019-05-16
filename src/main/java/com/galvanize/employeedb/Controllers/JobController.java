package com.galvanize.employeedb.Controllers;

import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Entities.Job;
import com.galvanize.employeedb.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/add")
    public Job save(@RequestBody Job job) {
        return jobService.saveJob(job);
    }

    @GetMapping("/get/{id}")
    public Optional<Job> save(@PathVariable("id") Long id) {
        return jobService.getJobById(id);
    }


    @PutMapping("/put/{id}")
    public Job updateCustomer(@PathVariable("id") Long id, @RequestBody ModelMap modelMap) {

        Job updateJob = jobService.getJobById(id).get();
        updateJob.setCustomer(modelMap.get("customer").toString());
        return this.jobService.saveJob(updateJob);
    }
}
