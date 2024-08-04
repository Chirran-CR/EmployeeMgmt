package com.assignment.service;

import com.assignment.model.Employee;
import com.assignment.repository.EmpRepository;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpService implements IEmpService{
    @Autowired
    private EmpRepository empRepository;

    @Override
    public Employee createEmp(Employee emp) {
       return  empRepository.save(emp);
    }

    @Override
    public Employee getEmpById(String Id) throws Exception{
        Employee emp = empRepository.findById(Id).get();
        return emp;
    }

    @Override
    public List<Employee> getEmpByName(String name) throws Exception{
        List<Employee> emp = empRepository.findByName(name);
        return emp;
    }

    @Override
    public Employee updateEmp(String Id, Map<String, Object> updatedData) throws Exception {
        Optional<Employee> optEmp = empRepository.findById(Id);
        if(optEmp.isEmpty()){
            throw new Exception("No employee found for this id");
        }
        Employee emp = optEmp.get();
        updatedData.forEach((key,val)->{
            Field field = ReflectionUtils.findField(Employee.class,key);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field,emp,val);
            }
        });

        return empRepository.save(emp);
    }

    @Override
    public void deleteEmpById(String Id)throws Exception {
        empRepository.deleteById(Id);
    }

    @Override
    public void deleteEmployeesByName(String name) throws Exception{
        empRepository.deleteEmployeesByName(name);
    }
}
