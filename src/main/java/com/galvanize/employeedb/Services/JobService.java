package com.galvanize.employeedb.Services;

import com.galvanize.employeedb.Entities.Job;
import com.galvanize.employeedb.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository repository;

    public JobService() {
    }

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Job saveJob(Job job) {
        return this.repository.save(job);
    }

    public Optional<Job> getJobById(Long id) {
        return this.repository.findById(id);
    }


}
