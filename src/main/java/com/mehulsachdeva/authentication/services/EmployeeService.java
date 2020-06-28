package com.mehulsachdeva.authentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.authentication.models.Employee;
import com.mehulsachdeva.authentication.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ResponseBuilder responseBuilder;

    public Map<String, String> fetchEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    new ObjectMapper().writeValueAsString(employees),
                    Constants.NO_ERROR
            );
        }catch(Exception e) {
            return responseBuilder.createResponse(
                    Constants.FAILURE_STATUS,
                    Constants.FETCH_FAILURE_RESPONSE,
                    Constants.EXCEPTION_RAISED + String.valueOf(e)
            );
        }
    }
}
