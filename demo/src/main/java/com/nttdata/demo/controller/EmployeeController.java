package com.nttdata.demo.controller;

import com.nttdata.demo.entity.Company;
import com.nttdata.demo.entity.Employee;
import com.nttdata.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-service")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create-employee")
    public Employee createEmployee(Employee employee){
        return employeeService.saveOrUpdateEmployee(employee);
    }

    @PutMapping("/update-employee")
    public Employee updateEmployee(Employee employee){
        return employeeService.saveOrUpdateEmployee(employee);
    }

    @DeleteMapping("/delete-employee/{id}")
    public String deleteEmployee(@PathVariable Integer id){
        return employeeService.deleteEmployee(id).get(false);
    }

    @GetMapping("/get-employee/{id}")
    public Employee getEmployee(@PathVariable Integer id){
        return employeeService.getEmployee(id);
    }

    @PostMapping("/get-all-employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping("/createEmployeeValidated")
    public Employee saveValidatedCompany(@Valid @RequestBody Employee employee) {
        return employeeService.saveOrUpdateEmployee(employee);
    }

}
