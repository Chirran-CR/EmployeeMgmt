package com.assignment.controller;

import com.assignment.model.Employee;
import com.assignment.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/emp")
public class EmpController {

    private static final Logger LOG = LoggerFactory.getLogger(EmpController.class);

    @Autowired
    EmpService empService;

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(@RequestBody Employee empRequest){
        LOG.info("EmpController:: createEmployee method started ");
        try{
            Object response = empService.createEmp(empRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            LOG.error("EmpController:: Exception in createEmployee: ",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String Id){
        LOG.info("EmpController:: getEmployeeById method started ");
        try{
            Employee emp = empService.getEmpById(Id);
            return new ResponseEntity<>(emp, HttpStatus.OK);
        }catch(Exception e){
            LOG.error("EmpController:: Exception in getEmployeeById: ",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable String name){
        LOG.info("EmpController:: getEmployeeByName started ");
        try{
            List<Employee> emp = empService.getEmpByName(name);
            return new ResponseEntity<>(emp,HttpStatus.OK);
        }catch(Exception e){
            LOG.error("EmpController:: Exception in getEmployeeByName: ",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Map<String, Object> empData, @PathVariable String id){
        LOG.info("EmpController:: updateEmployee started ");
        System.out.println("val of empData is:"+empData);
        try{
            Employee updatedEmp = empService.updateEmp(id, empData);
            return new ResponseEntity<>(updatedEmp,HttpStatus.OK);
        }catch(Exception e){
            LOG.error("EmpController:: Exception in updateEmployee: ",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String id){
        LOG.info("EmpController:: deleteEmployee started ");
        try{
           empService.deleteEmpById(id);
           return new ResponseEntity<>("Emp data deleted",HttpStatus.OK);
        }catch (Exception e){
            LOG.error("EmpController:: Exception in deleteEmployee: ",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deletebyname/{name}")
    public ResponseEntity<Object> deleteEmployeeByName(@PathVariable String name){
        LOG.info("EmpController:: deleteEmployeeByName started ");
        try{
            empService.deleteEmployeesByName(name);
            return new ResponseEntity<>("Employee Deleted",HttpStatus.OK);
        }catch (Exception e){
            LOG.error("EmpController:: Exception in deleteEmployeeByName: ",e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
