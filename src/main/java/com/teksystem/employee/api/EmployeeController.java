package com.teksystem.employee.api;


import com.teksystem.employee.dto.EmployeeDto;
import com.teksystem.employee.entity.Employee;
import com.teksystem.employee.entity.HourlyEmployee;
import com.teksystem.employee.entity.Manager;
import com.teksystem.employee.entity.SalariedEmployee;
import com.teksystem.employee.enums.EmployeeType;
import com.teksystem.employee.exceptions.InvalidEmployeeTypeException;
import com.teksystem.employee.exceptions.UserNotExistException;
import com.teksystem.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

import static com.teksystem.employee.constants.Applicationconstants.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final Map<Integer, Employee> managers = new HashMap<>();
    private final Map<Integer, Employee> salariedEmployees = new HashMap<>();
    private final Map<Integer, Employee> hourlyEmployees = new HashMap<>();

    public EmployeeController() {
        RandomGenerator random = RandomGenerator.getDefault();
        for (int i = 1; i <= 10; i++) {
            managers.put(i, new Manager(i));
            salariedEmployees.put(i, new SalariedEmployee(i));
            hourlyEmployees.put(i, new HourlyEmployee(i));
        }
    }

    /**
     * @return all employees
     */
    @GetMapping("/all")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeesList = new ArrayList<>();
        employeesList.addAll(employeeService.getEmployeeDto(managers));
        employeesList.addAll(employeeService.getEmployeeDto(salariedEmployees));
        employeesList.addAll(employeeService.getEmployeeDto(hourlyEmployees));
        return employeesList;
    }

    /**
     * @param empType
     * @return all employees by type
     */
    @GetMapping("/{empType}")
    public List<EmployeeDto> getAllEmployeesByType(@PathVariable("empType") String empType) {
        List<EmployeeDto> employeesList = new ArrayList<>();
        EmployeeType type = EmployeeType.valueOf(empType.toUpperCase());
        if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_MANAGER))
            employeesList.addAll(employeeService.getEmployeeDto(managers));
        else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_SALARIED))
            employeesList.addAll(employeeService.getEmployeeDto(salariedEmployees));
        else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_HOURLY))
            employeesList.addAll(employeeService.getEmployeeDto(hourlyEmployees));
        return employeesList;
    }

    /**
     * @param empType
     * @param empId
     * @return employee by filtering type and id
     */
    @GetMapping("/{empType}/{empId}")
    public EmployeeDto getEmployeeByTypeAndId(@PathVariable("empType") String empType, @PathVariable("empId") Integer empId) {
        if (empId <= 0 || empId > 10) {
            throw new UserNotExistException("User not exists with employee id: " + empId);
        }
        EmployeeType type = EmployeeType.valueOf(empType.toUpperCase());
        if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_MANAGER))
            return managers.get(empId).toDto();
        else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_SALARIED))
            return salariedEmployees.get(empId).toDto();
        else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_HOURLY))
            return hourlyEmployees.get(empId).toDto();
        else
            throw new InvalidEmployeeTypeException("Invalid employee type.");
    }

    /**
     * @param empType
     * @param empId
     * @param workingDays
     * @return details about working days and no of vacations
     */
    @GetMapping("/work/{empType}/{empId}")
    public EmployeeDto calculateVacations(@PathVariable("empType") String empType, @PathVariable("empId") Integer empId, @RequestParam("workingDays") Integer workingDays) {
        if (empId <= 0 || empId > 10) {
            throw new UserNotExistException("User not exists with employee id: " + empId);
        }
        EmployeeType type = EmployeeType.valueOf(empType.toUpperCase());
        Employee employee;
        if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_MANAGER)) {
            employee = managers.get(empId);
        } else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_SALARIED)) {
            employee = salariedEmployees.get(empId);
        } else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_HOURLY)) {
            employee = hourlyEmployees.get(empId);
        } else {
            throw new InvalidEmployeeTypeException("Invalid Employee type");
        }
        employee.work(workingDays);
        return employee.toDto();


    }

    /**
     * @param empType
     * @param empId
     * @param vacationDays
     * @return balance vacation days after availing
     */
    @GetMapping("/vacation/{empType}/{empId}")
    public EmployeeDto takeVacation(@PathVariable("empType") String empType, @PathVariable("empId") Integer empId, @RequestParam("vacationDays") Integer vacationDays) {
        if (empId <= 0 || empId > 10) {
            throw new UserNotExistException("User not exists with employee id" + empId);
        }
        EmployeeType type = EmployeeType.valueOf(empType.toUpperCase());
        Employee employee;
        if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_MANAGER)) {
            employee = managers.get(empId);
        } else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_SALARIED)) {
            employee = salariedEmployees.get(empId);
        } else if (type.name().equalsIgnoreCase(EMPLOYEE_TYPE_HOURLY)) {
            employee = hourlyEmployees.get(empId);
        } else {
            throw new InvalidEmployeeTypeException("Invalid Employee type");
        }
        employee.takeVacation(vacationDays);
        return employee.toDto();
    }

}
