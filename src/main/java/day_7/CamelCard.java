package day_7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CamelCard {

    private CamelCard.Hand Hand;

    class Hand implements Comparable<Hand>{
        private String hand;

        Hand(String myHand){
            this.hand = myHand;
        }
        public String getHand() {
            return hand;
        }

        @Override
        public int compareTo(Hand o) {

            if(getHandScore(this.getHand()) != getHandScore(o.getHand()))
                return getHandScore(this.getHand()) - getHandScore(o.getHand());
            else return compareSingleCard(this.getHand(), o.getHand());
        }

        @Override
        public String toString() {
            return this.hand;
        }

        private int getHandScore(String hand){
            ArrayList<Character> al = new ArrayList<>();
            boolean containJoker = hand.contains("J");
            for (int i = 0; i < hand.length(); i++) {
                al.add(hand.charAt(i));
            }
            HashMap<Character, Integer> hm = new HashMap<>();
            for (int i = 0; i < hand.length(); i++) {
                hm.putIfAbsent(al.get(i),
                        Collections.frequency(al,al.get(i)));
            }
            int score = 0;
            if (!containJoker){
                for(int i : hm.values()){
                    score = score + (int) Math.pow(10,i);
                }
            }else{
                int jokers = hm.get('J');
                ArrayList<Integer> numbers = new ArrayList<>();
                for (Character c : hm.keySet()){
                    if (c != 'J') numbers.add(hm.get(c));
                }
                if (numbers.isEmpty()) return (int) Math.pow(10, 5) - 1;
                Collections.sort(numbers, Collections.reverseOrder());
                int tmp = numbers.get(0);
                numbers.set(0, tmp + jokers);
                for (int i : numbers){
                    score = score + (int) Math.pow(10, i);
                }
            }
            return score;
        }

        private int compareSingleCard(String hand, String hand1) {
            String cardOrder = "J23456789TQKA";
            int result = 0;
            for (int i = 0; i < hand.length();){
                if (hand.charAt(i) == hand1.charAt(i)) i++;
                else{
                    result = cardOrder.indexOf(hand.charAt(i)) - cardOrder.indexOf(hand1.charAt(i));
                    break;
                }
            }
             return result;
        }
    }

    public static void main(String[] args) {

        SortedMap<Hand, Integer> sortedMap = new TreeMap<>();
        try {
            Scanner scanner = new Scanner(new File("D:\\Java\\Advent_of_Code\\src\\main\\java\\day_7\\input.txt"));
            while (scanner.hasNextLine()){
                String[] arr = scanner.nextLine().split(" ");
                sortedMap.put(new CamelCard().new Hand(arr[0]), Integer.parseInt(arr[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int partOne = 0;
        int i = 1;
        for (CamelCard.Hand hand : sortedMap.keySet()){
            System.out.println(hand);
            partOne = partOne + sortedMap.get(hand) * i;
            i++;
        }
        System.out.println("Part two solution: " + partOne);
    }

}
