package org.example;

/*methods for program*/
public class Utilities {
    public static Integer stringToInteger(String line) {
        Integer integer;
        try {
            integer = Integer.valueOf(line);
            return integer;
        } catch (Exception e) {
            return null;
        }
    }

    public static char stringToChar(String line) {
        char el;
        try {
            el = line.charAt(0);
            return el;
        } catch (Exception e) {
            return 0;
        }
    }

    public static Square[][] deepCopy(Square[][] original) {
        if (original[0] == null) {
            return null;
        }
        Square[][] answer = new Square[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original[0].length; j++) {
                answer[i][j] = original[i][j].getCopy();
            }
        }
        return answer;
    }
}
