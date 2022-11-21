package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Manager(100,2));
        employees.add(new Programmer(100,3));
        employees.add(new Secretary(100,2, 5));
        for (var employee: employees){
            if (employee instanceof Gradable employeeGradable){
                System.out.println(employeeGradable.dropGrade());
                System.out.println(employeeGradable.getCurrentGrade());
                System.out.println(employeeGradable.increaseGrade());
            }
        }

    }
}
