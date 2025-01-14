package com.teksystem.employee.service;


import com.teksystem.employee.dto.EmployeeDto;
import com.teksystem.employee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public List<EmployeeDto> getEmployeeDto(Map<Integer, Employee> employees) {
        return employees.values().stream().map(Employee::toDto).collect(Collectors.toList());
    }
}
