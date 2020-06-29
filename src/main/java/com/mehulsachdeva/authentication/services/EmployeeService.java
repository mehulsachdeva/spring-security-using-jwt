package com.mehulsachdeva.authentication.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mehulsachdeva.authentication.models.Employee;
import com.mehulsachdeva.authentication.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Map<String, String> addEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    Constants.INSERT_SUCCESS_RESPONSE,
                    Constants.NO_ERROR
            );
        }catch(Exception e) {
            return responseBuilder.createResponse(
                    Constants.FAILURE_STATUS,
                    Constants.INSERT_FAILURE_RESPONSE,
                    Constants.EXCEPTION_RAISED + String.valueOf(e)
            );
        }
    }

    public Map<String, String> updateEmployee(Employee employee) {
        try {
            Optional<Employee> employeeContainer = employeeRepository.findById(employee.getEmployeeId());
            if(employeeContainer.isPresent()) {
                employeeRepository.save(employee);
                return responseBuilder.createResponse(
                        Constants.SUCCESS_STATUS,
                        Constants.UPDATE_SUCCESS_RESPONSE,
                        Constants.NO_ERROR
                );
            }else {
                return responseBuilder.createResponse(
                        Constants.SUCCESS_STATUS,
                        Constants.NO_EMPLOYEE_FOUND_WITH_ID,
                        Constants.NO_ERROR
                );
            }
        }catch(Exception e){
            return responseBuilder.createResponse(
                    Constants.SUCCESS_STATUS,
                    Constants.UPDATE_FAILURE_RESPONSE,
                    Constants.EXCEPTION_RAISED + String.valueOf(e)
            );
        }
    }
}
