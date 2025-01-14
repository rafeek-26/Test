package com.teksystem.employee.entity;

import com.teksystem.employee.dto.EmployeeDto;
import com.teksystem.employee.exceptions.InvalidWorkingDaysException;
import com.teksystem.employee.exceptions.VacationDaysAvailException;

public abstract class Employee {
    private int workDays = 0;
    private final int empId;
    private float vacationDaysAccumulated = 0;
    private static final float MAX_WORK_DAYS = 260;

    public Employee(int empId) {
        this.empId = empId;

    }

    public float getVacationDaysAccumulated() {
        return vacationDaysAccumulated;
    }

    protected abstract float getVacationDaysAccruedPerYear();

    protected abstract String getType();

    public void work(int daysWorked) {
        this.workDays = daysWorked;
        if (daysWorked < 0 || daysWorked > MAX_WORK_DAYS) {
            throw new InvalidWorkingDaysException("Invalid number of days worked. Must be between 0 and " + MAX_WORK_DAYS);
        }
        vacationDaysAccumulated = (getVacationDaysAccruedPerYear() / MAX_WORK_DAYS) * daysWorked;
    }

    public void takeVacation(float daysTaken) {
        if (vacationDaysAccumulated == 0) {
            throw new VacationDaysAvailException("No vacation days left. Please verify/post your working days.");
        }
        if (daysTaken < 0) {
            throw new VacationDaysAvailException("Vacation days can not be negative.");
        }

        if (daysTaken > vacationDaysAccumulated) {
            throw new IllegalArgumentException("Not enough vacation days.");
        }
        vacationDaysAccumulated = vacationDaysAccumulated - daysTaken;
    }

    public int getWorkDays() {
        return workDays;
    }

    public int getEmpId() {
        return empId;
    }

    public EmployeeDto toDto() {
        return new EmployeeDto(this.empId, this.workDays, this.vacationDaysAccumulated, this.getType());
    }
}
