package com.teksystem.employee.entity;

public class SalariedEmployee extends Employee {
     public SalariedEmployee(int empId){
         super(empId);
     }
    @Override
    protected float getVacationDaysAccruedPerYear() {
        return 15;
    }

    @Override
    protected String getType() {
        return "Salaried";
    }
}
