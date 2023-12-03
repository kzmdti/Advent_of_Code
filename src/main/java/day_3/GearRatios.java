package day_3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;

public class GearRatios {

    public int total = 0;
    public int numLines = 0;
    public int lineLength = 0;
    public Character[][] matrix = null;

    public static void main(String[] args) {

        List<String> allLines = new ArrayList<String>();
        try {
            allLines = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GearRatios ratios = new GearRatios();
        ratios.total = 0;

        ratios.numLines = allLines.size();
        ratios.lineLength = allLines.get(0).length();
        ratios.matrix = new Character[ratios.numLines][ratios.numLines];

        for (String line : allLines) {
            for (int i = 0; i < ratios.lineLength; i++) {
                ratios.matrix[allLines.indexOf(line)][i] = line.charAt(i);
            }
        }

        for (int i = ratios.numLines -1; i >= 0; i--) {
            // Read all lines
            for (int j = ratios.numLines -1; j >= 0; j--) {
                // Read character
                if( Character.isDigit(ratios.matrix[i][j]) ) {
                    j = ratios.checkNumber(i,j);
                }
            }
        }
        System.out.println(ratios.total);

    }//end of main

    private int checkNumber(int line, int column) {
        int power = 0, value = 0, j;
        for (j = column; j >= 0 && Character.isDigit(matrix[line][j]); j--) {
            value = value + Character.getNumericValue(matrix[line][j]) * (int) Math.pow(10, power);
            power++;
        }
        if (hasSymbol(line, j+1, column)) total = total + value;
        return j;
    }

    private boolean hasSymbol(int line, int startColumn, int endColumn) {
        int upperLine = line == 0 ? 0 : line - 1;
        int lowerLine = line == numLines - 1? numLines - 1 : line + 1;
        int leftColumn = startColumn == 0? 0 : startColumn - 1;
        int rightColumn = endColumn == lineLength - 1? lineLength - 1 : endColumn + 1;
        for (int i = upperLine ; i <= lowerLine; i++) {
            for (int j = leftColumn; j <= rightColumn; j++) {
                if (!Character.isDigit(matrix[i][j]) && matrix[i][j]!= '.') {
                    return true;
                }
            }
        }
        return false;
    }


}//end of class