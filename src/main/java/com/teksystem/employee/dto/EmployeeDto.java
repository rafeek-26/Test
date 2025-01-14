package com.teksystem.employee.dto;

public class EmployeeDto {
    private int empId;
    private int workingDays;
    private float vacations;
    private String type;

    public EmployeeDto(int empId, int workingDays, float vacations, String type) {
        this.empId = empId;
        this.workingDays = workingDays;
        this.vacations = vacations;
        this.type = type;
    }

    public int getEmpId() {
        return empId;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public float getVacations() {
        return vacations;
    }

    public String getType() {
        return type;
    }
}
