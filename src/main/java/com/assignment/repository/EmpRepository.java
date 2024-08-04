package com.assignment.repository;

import com.assignment.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpRepository extends MongoRepository<Employee,String> {
//    @Override
    Optional<Employee> findById(String Id);

    Optional<Employee> findByName(String name);
//    Optional<Employee> createEmployee(Employee emp);
}
