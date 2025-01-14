package com.teksystem.employee.entity;

import org.springframework.stereotype.Component;

public class Manager extends Employee {
     public Manager(int empId){
         super(empId);
     }
    @Override
    protected float getVacationDaysAccruedPerYear() {
        return 30;
    }

    @Override
    protected String getType() {
        return "Manager";
    }
}
