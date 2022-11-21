package org.example;

public abstract class Employee {
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public void increaseSalary(Integer incr) {
        salary +=incr;
    }
    public void decreaseSalary(Integer dec) {
        salary -=dec;
    }

    private void setIq(Integer iq) {
        Iq = iq;
    }

    public Integer getIq() {
        return Iq;
    }

    public Employee(Integer salary, Integer iq) {
        this.salary = salary;
        Iq = iq;
    }

    static void chill(Integer time) throws InterruptedException {
        Thread.sleep(time);
    }

    static void chill() throws InterruptedException {
        Thread.sleep(10);
    }

    abstract String doWork() throws InterruptedException;

    @Override
    public String toString() {
        return "with " + salary + " salary and " + Iq + " Iq";
    }

    private Integer salary;
    private Integer Iq;
}
