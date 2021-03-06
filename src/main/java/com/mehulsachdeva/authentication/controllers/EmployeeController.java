package com.mehulsachdeva.authentication.controllers;

import com.mehulsachdeva.authentication.models.Employee;
import com.mehulsachdeva.authentication.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/example")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Map<String, String> addEmployee(Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PostMapping("/employees")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Map<String, String> fetchEmployees() {
        return employeeService.fetchEmployees();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('MANAGER') or (hasAuthority('EMPLOYEE') and @jwtUserSecurity.hasAccess(#employee, authentication))")
    public Map<String, String> updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }
}
