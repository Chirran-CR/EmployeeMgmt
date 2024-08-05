package com.assignment.service;

import com.assignment.model.Employee;
import com.assignment.repository.EmpRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EmpServiceTest {
    private static final String id = "1";
    private static final String name = "Chirran";
    private static final String address = "Bhubaneswar";
    private static final String dob = "10052000";
    private static final String number = "123445";
    private Employee emp;

    @InjectMocks
    private EmpService empService;

    @Mock
    private EmpRepository empRepository;

    @Test
    public void shouldCreateEmployee(){
        emp = getEmployee();
        //when then
        when(empRepository.save(emp)).thenReturn(emp);
        //assertion
        assertEquals(emp,empService.createEmp(emp));
    }

    @Test
    public void shouldGetEmpById() throws  Exception{
        emp = getEmployee();
        when(empRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        assertEquals(emp,empService.getEmpById(emp.getId()));
    }

    @Test
    public void shouldGetEmpByIdThrowsException(){
        emp = getEmployee();
        //set emp as empty
        when(empRepository.findById(emp.getId())).thenReturn(Optional.empty());
        //throw exception
        assertThrows(Exception.class,()->{
           empService.getEmpById(emp.getId());
        });
    }

    @Test
    public void shouldGetEmpByName() throws Exception{
        emp = getEmployee();
        Employee emp1 = getEmployee();
        emp1.setId("2");
        List<Employee> empList = Arrays.asList(emp,emp1);
        when(empRepository.findByName(emp.getName())).thenReturn(empList);
        assertEquals(empList, empService.getEmpByName(emp.getName()));
    }

    @Test
    public void shouldUpdateEmployee() throws Exception{
        emp = getEmployee();
        Employee updatedEmp = getEmployee();
        updatedEmp.setName("Chirran-2");
        updatedEmp.setNumber("9876");
        when(empRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        when(empRepository.save(any(Employee.class))).thenReturn(updatedEmp);
        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("name","Chirran-2");
        updatedData.put("number","9876");

        assertEquals(updatedEmp,empService.updateEmp(emp.getId(),updatedData));
    }

    @Test
    public void shouldDeleteEmployeeById() throws Exception{
        emp = getEmployee();
//        when(empRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        doNothing().when(empRepository).deleteById(emp.getId());
        empService.deleteEmpById(emp.getId());
    }

    @Test
    public void shouldDeleteEmployeeByName() throws Exception{
        emp = getEmployee();
//        when(empRepository.findById(emp.getId())).thenReturn(Optional.of(emp));
        doNothing().when(empRepository).deleteEmployeesByName(emp.getName());
        empService.deleteEmployeesByName(emp.getName());
    }

    private Employee getEmployee(){
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setAddress(address);
        emp.setDob(dob);
        emp.setNumber(number);
        return emp;
    }
}
