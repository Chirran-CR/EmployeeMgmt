package com.assignment.service;

import com.assignment.model.Employee;

import java.util.Map;

public interface IEmpService {
    Employee createEmp(Employee emp);
    Employee getEmpById(String Id) throws Exception;
    Employee getEmpByName(String name)throws Exception;
    Employee updateEmp(String Id, Map<String, Object> updatedData) throws Exception;
    void deleteEmpById(String Id) throws Exception;
}
