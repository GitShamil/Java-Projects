package org.example;

import java.util.Random;

public class Programmer extends Employee implements Gradable {
    private Position position;

    public Programmer(Integer salary, Integer iq, String position) {
        super(salary, iq);
        setPosition(position);
    }

    public Programmer(Integer salary, Integer iq) {
        super(salary, iq);
        position = Position.Junnior;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(String position) {
        this.position = Position.valueOf(position);
    }

    public String increasePosition() {
        var positions = Position.values();
        for (int i = 0; i < positions.length; ++i) {
            if (positions[i] == this.position) {
                if (i != positions.length - 1) {
                    this.position = positions[i + 1];
                    return "Positions upgraded to " + position;
                } else {
                    return "Position already max " + position;
                }
            }
        }
        return null;
    }

    public String decreasePosition() {
        var positions = Position.values();
        for (int i = 0; i < positions.length; ++i) {
            if (positions[i] == this.position) {
                if (i != 0) {
                    this.position = positions[i - 1];
                    return "Positions dropped to " + position;
                } else {
                    return "Position already min " + position;
                }
            }
        }
        return null;
    }

    @Override
    String doWork() throws InterruptedException {
        Thread.sleep(5);
        Random rand = new Random();
        return "I've worked good and create: " + rand.nextInt() + "minutes";
    }

    @Override
    public String toString() {
        return "Programmer " + super.toString();
    }

    @Override
    public String increaseGrade() {
        return increasePosition();
    }

    @Override
    public String dropGrade() {
        return decreasePosition();
    }

    @Override
    public String getCurrentGrade() {
        return position.toString();
    }
}
