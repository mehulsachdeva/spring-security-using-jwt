package com.mehulsachdeva.authentication.controllers;

import com.mehulsachdeva.authentication.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/example")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employees")
    public Map<String, String> fetchEmployees() {
        return employeeService.fetchEmployees();
    }
}
