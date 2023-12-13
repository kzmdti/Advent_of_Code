package day_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CosmicExpansion {

    class Galaxy{
        public int x;
        public int y;

        public Galaxy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x: " + this.x + " y: " + this.y ;
        }
    }

    CosmicExpansion(){}
    List<Galaxy> galaxies = new ArrayList<>();

    public static void main(String[] args) {

        CosmicExpansion cosmos = new CosmicExpansion();
        Set<Integer> lines = new HashSet<>();
        Set<Integer> columns = new HashSet<>();

        List<String> allLines = new ArrayList<>();
        try {
            allLines = Files.readAllLines(Paths.get("D:\\Java\\Advent_of_Code\\src\\main\\java\\day_11\\input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int stringLength = allLines.get(0).length();
        int numberLines = allLines.size();
        for (int i = 0; i < numberLines ; i++){
            lines.add(i);
        }
        for (int i = 0; i < stringLength ; i++){
            columns.add(i);
        }

        int j = 0;
        for (String str : allLines){
            for (int c = 0; c < stringLength; c++){
                char ch = str.charAt(c);
                if ( ch == '#') {
                    cosmos.galaxies.add(cosmos.new Galaxy(j,c));
                    columns.remove(c);
                    lines.remove(j);
                }
            }
            j++;
        }
        cosmos.expand(lines, columns);

        System.out.println("After expansion:");
        System.out.println(cosmos.galaxies);

        System.out.println("Distance:");
        System.out.println(cosmos.total_distance());





    }

    public void expand(Set<Integer> lines, Set<Integer> columns){
        int temp = 0;
        for(Galaxy gal : this.galaxies){
            for(int i : lines){
                if (gal.x > i) temp++;
            }
            gal.x = gal.x + temp;
            temp = 0;
        }
        for(Galaxy gal : this.galaxies){
            for(int i : columns){
                if (gal.y > i) temp++;
            }
            gal.y = gal.y + temp;
            temp = 0;
        }
    }

    public int total_distance(){
        int sum = 0;
        for(int i = 0; i < this.galaxies.size() - 1; i++){
            for (int j = i+1; j < this.galaxies.size(); j++){
                int pairDistance = Math.abs(this.galaxies.get(i).x - this.galaxies.get(j).x)
                                    + Math.abs(this.galaxies.get(i).y - this.galaxies.get(j).y);
                sum = sum + pairDistance;
            }
        }
        return sum;
    }

}
