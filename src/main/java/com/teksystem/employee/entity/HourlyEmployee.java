package com.teksystem.employee.entity;

public class HourlyEmployee extends Employee {
    public HourlyEmployee(int empId) {
        super(empId);
    }

    @Override
    protected float getVacationDaysAccruedPerYear() {
        return 10;
    }

    @Override
    protected String getType() {
        return "Hourly";
    }
}
