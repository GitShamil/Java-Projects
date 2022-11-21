package org.example;

import java.util.Vector;

public class Executive extends Manager{
    private Vector<Plan> plans;

    public Vector<Plan> getPlans() {
        return plans;
    }

    public Plan getLastPlan(){
        return plans.lastElement();
    }

    public void addPlan(Plan plan){
        plans.add(plan);
    }

    public Executive(Integer salary, Integer iq, Vector<Employee> employees) {
        super(salary, iq, employees);
    }

    public Executive(Integer salary, Integer iq) {
        super(salary, iq);
    }

    @Override
    String doWork() throws InterruptedException {
        return "planing something with" + getEmployees().size() + "employees";
    }

    @Override
    public String toString() {
        return "Executive "+super.toString();
    }
}

