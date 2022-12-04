package org.example;

import java.util.Objects;

/*class of one square
 * */
public final class Square {
    //    color
    private Integer colorOfSquare;
    //   available or not
    private boolean available;
    //    coordinate x
    private final Integer i;
    //    coordinate y
    private final Integer j;

    public Integer getColorOfSquare() {
        return colorOfSquare;
    }

    public void setColorOfSquare(Integer colorOfSquare) {
        if (!isAvailable()) {
            if (!isEmpty()) {
                throw new IllegalArgumentException("Square isn't empty!!!");
            } else {
                throw new IllegalArgumentException("Square isn't available");
            }
        }
        setAvailable(false);
        this.colorOfSquare = colorOfSquare;
    }

    public void setNotAvailableSquare(Integer colorOfSquare) {
        this.colorOfSquare = colorOfSquare;
    }

    public Square(Integer i, Integer j) {
        this.i = i;
        this.j = j;
        colorOfSquare = 0;
        available = false;
    }

    public Boolean isBlack() {
        return colorOfSquare == 2;
    }

    public Boolean isWhite() {
        return colorOfSquare == 1;
    }

    public Boolean isEmpty() {
        return colorOfSquare == 0;
    }


    public Square getCopy() {
        Square ans = new Square(this.i, this.j);
        ans.colorOfSquare = this.getColorOfSquare();
        ans.setAvailable(this.isAvailable());
        return ans;
    }

    public Integer getI() {
        return i;
    }

    public Integer getJ() {
        return j;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Square sq) {
            return (Objects.equals(this.i, sq.i)) && (Objects.equals(this.j, sq.j));
        }
        return false;
    }

    @Override
    public String toString() {
        switch (colorOfSquare) {
            case 0:
                if (!isAvailable())
                    return String.valueOf('◯');
                else
                    return String.valueOf('◍');
            case 1:
                return String.valueOf('❎');
            case 2:
                return String.valueOf('♡');
            default:
                return null;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isTheSameColor(Integer a) {
        return Objects.equals(a, getColorOfSquare());
    }

    public boolean isOppositeColor(Integer a) {
        return (a + getColorOfSquare() == 3);
    }

    public void changeColorOnOpposite() {
        if (colorOfSquare == 0) {
            throw new RuntimeException("Square doesn't have color, " +
                    "but you want to change on opposite!");
        }
        colorOfSquare = 3 - colorOfSquare;
    }

    //    check if this square at corner
    public boolean isCorner() {
        return (i == 0 && j == 0) || (i == 7 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 7);
    }

    //    check if this square is at edge
    public boolean isEdge() {
        return (i == 0 && j != 0 && j != 7) || (i == 7 && j != 0 && j != 7)
                || (j == 7 && i != 7 && i != 0) || (j == 0 && i != 7 && i != 0);
    }
}
