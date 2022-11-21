package org.example;

import java.util.Vector;

public class Manager extends Employee implements Gradable{
    private Vector<Employee> employees;

    public Vector<Employee> getEmployees() {
        return employees;
    }

    protected void setEmployees(Vector<Employee> employees) {
        this.employees = employees;
    }

    public Manager(Integer salary, Integer iq, Vector<Employee> employees) {
        super(salary, iq);
        setEmployees(employees);
    }

    public Manager(Integer salary, Integer iq) {
        super(salary, iq);
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }


    @Override
    String doWork() throws InterruptedException {
       return "Asked" + employees.size() + "employees";
    }

    @Override
    public String toString() {
        return "Manager "+super.toString();
    }

    @Override
    public String increaseGrade() {
        increaseSalary(100);
        return "Salary has increased to " + getSalary();
    }

    @Override
    public String dropGrade() {
        decreaseSalary(100);
        return "Salary has decreased to " + getSalary();
    }

    @Override
    public String getCurrentGrade() {
        return "current salary is "+getSalary();
    }
}
