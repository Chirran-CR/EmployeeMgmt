package com.assignment.controller;

import com.assignment.model.Employee;
import com.assignment.service.EmpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebMvcTest(EmpController.class)
public class EmpControllerTest {
    private static final String id = "1";
    private static final String name = "Chirran";
    private static final String address = "Bhubaneswar";
    private static final Date dob;

    static {
        try {
            dob = new SimpleDateFormat("dd-MM-yyyy").parse("10-05-2000");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final long number = 909089899L;
    private Employee emp;

    @MockBean
    EmpService empService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void createEmpTest() throws Exception {
        emp = getEmployee();
        when(empService.createEmp(emp)).thenReturn(emp);
        mockMvc.perform(post("/emp/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(emp)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {
        emp = getEmployee();
        when(empService.getEmpById(emp.getId())).thenReturn(emp);
        mockMvc.perform(get("/emp/get/{id}",emp.getId())
            ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Chirran"));
    }

    @Test
    public void getEmployeeByNameTest() throws Exception {
        emp = getEmployee();
        Employee emp1 = getEmployee();
        emp1.setId("2");
        List<Employee> empList = Arrays.asList(emp,emp1);
        when(empService.getEmpByName(emp.getName())).thenReturn(empList);
        mockMvc.perform(get("/emp/getbyname/{name}",emp.getName()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].id").value("2"));
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        emp = getEmployee();
        doNothing().when(empService).deleteEmpById(emp.getId());
        mockMvc.perform(delete("/emp/delete/{id}",emp.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteEmployeeByName() throws Exception{
        emp = getEmployee();
        doNothing().when(empService).deleteEmployeesByName(emp.getName());
        mockMvc.perform(delete("/emp/deletebyname/{name}",emp.getName()))
                .andExpect(status().isOk());
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
