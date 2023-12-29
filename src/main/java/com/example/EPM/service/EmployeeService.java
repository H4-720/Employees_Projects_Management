package com.example.EPM.service;

import com.example.EPM.model.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface EmployeeService {
    void createEmployee(Employee employee);
    Employee getEmployeeById(Integer id);
    List<Employee> getAllEmployees();
    void deleteEmployee(Integer id);
}
