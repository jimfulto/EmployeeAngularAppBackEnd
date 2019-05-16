package com.galvanize.employeedb.Repositories;

import com.galvanize.employeedb.Entities.Employee;
import com.galvanize.employeedb.Entities.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
}
