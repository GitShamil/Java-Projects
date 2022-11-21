package org.example;

import java.util.Random;

public class Secretary extends Employee {
    private Integer numberOfTable;

    public Integer getNumberOfTable() {
        return numberOfTable;
    }

    public void setNumberOfTable(Integer numberOfTable) {
        this.numberOfTable = numberOfTable;
    }

    public Secretary(Integer salary, Integer iq, Integer numberOfTable) {
        super(salary, iq);
        setNumberOfTable(numberOfTable);
    }

    @Override
    String doWork() throws InterruptedException {
        Thread.sleep(100);
        Random rand = new Random();
        return "I've worked good and count: " + rand.nextInt() + "numbers";
    }

    @Override
    public String toString() {
        return "Secretary "+super.toString();
    }
}
