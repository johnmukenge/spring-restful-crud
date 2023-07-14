package com.nttdata.demo.service;

import com.nttdata.demo.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee saveOrUpdateEmployee(Employee employee);
    Map<Boolean, String> deleteEmployee(Integer id);

    Employee getEmployee(Integer id);

    List<Employee> getAllEmployees();
}
