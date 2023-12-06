package day_6;

public class WaitForIt {

    static int[][] data = {{59,430},
                    {70,1218},
                    {78,1213},
                    {78,1276}};

    public static void main(String[] args) {
        int result = 1;

        for (int i = 0; i < data.length; i++){
            int race =  wayToBeatRecord((long) data[i][0], (long) data[i][1]);
            result = result * race;
            System.out.println("");
        }

        System.out.println("Part 1 solution = " + result);

        // Step 2
        // Build the two numbers
        String time = "";
        String dist = "";
        for (int i = 0; i < data.length; i++){
            time = time + data[i][0];
            dist = dist + data[i][1];
        }

        //System.out.println("Time " + time + "\n" + "Distance " + dist);
        System.out.println("Part 2 long solution: " + wayToBeatRecord(Long.parseLong(time),Long.parseLong(dist)));

    }

    public static int wayToBeatRecord(long time, long dist){
        double div = Math.sqrt((time * time) - ( 4 * dist));
        double sol_1 = (time + div) / 2;
        double sol_2 = (time - div) / 2;
        int oneOrZero = div % 1 == 0 ? 1 : 0;

        return (int) Math.abs(Math.abs((long) sol_2) - Math.abs((long) sol_1) - oneOrZero);
    }
}
