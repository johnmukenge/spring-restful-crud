package com.nttdata.demo.service;

import com.nttdata.demo.entity.Employee;
import com.nttdata.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveOrUpdateEmployee(Employee employee) {
        Employee savedEmployee = null;
        try {
            savedEmployee = employeeRepository.save(employee);
        }catch (IllegalArgumentException | OptimisticLockingFailureException e  ){
            e.printStackTrace();
        }
        return savedEmployee;
    }

    @Override
    public Map<Boolean, String> deleteEmployee(Integer id) {
        Map<Boolean, String> deletionStatus = new HashMap<>();
        if(id == null){
            deletionStatus.put(false, "Employee id is empty");
            return deletionStatus;
        }
        if(!employeeRepository.existsById(id)){
            deletionStatus.put(false, "Employee with id " + id + " does not exist");
            return deletionStatus;
        }
        employeeRepository.deleteById(id);
        deletionStatus.put(false, "Delete employee success");
        return deletionStatus;
    }

    @Override
    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
