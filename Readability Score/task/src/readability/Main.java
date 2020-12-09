package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("[.!?]");
        System.out.println(retCoun(input) > 10 ? "HARD" : "EASY");
    }

    public static int retCoun(String[] arr) {
        int sum = 0;
        int count = 0;
        int result;
        for (String w : arr) {
            String[] s = w.split(" ");
            sum += s.length;
            count++;
        }
        result = sum / count;
        return result;
    }
}
