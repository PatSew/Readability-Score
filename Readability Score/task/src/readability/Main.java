package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        String input = readFileAsString(args[0]);
        int w = countWords(input);
        int s = countSentences(input);
        int c = countChars(input);
        int sy = countSyllables(input);
        int po = countPolysyllables(input);
        choice(w, s, c, sy, po);
    }

    public static void choice(int w, int s, int c, int sy, int po) {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "ARI":
                aRI(c, w, s);
                break;
            case "FK":
                fK(w, s, sy);
                break;
            case "SMOG":
                sM(po, s);
                break;
            case "CL":
                cL(c, w, s);
                break;
            case "all":
                aRI(c, w, s);
                fK(w, s, sy);
                sM(po, s);
                cL(c, w, s);
                break;
            default:
                System.out.println("Unknown method");
                break;
        }
    }

    public static int countWords(String input) {
        int w = input.split("\\s").length;
        System.out.println("Words: " + w);
        return w;
    }

    public static int countSentences(String input) {
        int s = input.split("[.!?]").length;
        System.out.println("Sentences: " + s);
        return s;
    }

    public static int countChars(String input) {
        int c = 0;
        char[] temp = input.toCharArray();
        for (char ch : temp) {
            if (ch != ' ' && ch != '\t' && ch != '\n') {
                c++;
            }
        }
        System.out.println("Characters: " + c);
        return c;
    }

    public static int countSyllables(String input) {
        String[] words = input.split("\\s");
        int total = 0;
        for(String w : words) {
            total += countSyl(w);
        }
        System.out.println("Syllables: " + total);
        return total;
    }

    private static int countSyl(String word) {
        String i = "(?i)[aiouy][aeiouy]*|e[aeiouy]*(?!d?\\b)";
        Matcher m = Pattern.compile(i).matcher(word);
        int count = 0;

        while (m.find()) {
            count++;
        }

        // return at least 1
        return Math.max(count, 1);
    }

    public static int countPolysyllables(String input) {
        String[] words = input.split("\\s");
        int total = 0;
        for(String w : words) {
            int temp = countSyl(w);
            total += temp > 2 ? 1 : 0;
        }
        System.out.println("Polysyllables: " + total);
        return total;
    }

    public static double aRI (int c, int w, int s) {
        double result =  4.71 * ((double)c / (double)w) + 0.5 * ((double)w / (double)s) - 21.43;
        System.out.println("Automated Readability Index: " + String.format("%.2f", result) + retAge(result));
        return result;
    }

    public static double fK (int w, int s, int sy) {
        double result = 0.39 * ((double)w / (double)s) + 11.8 * ((double)sy / (double)w) - 15.59;
        System.out.println("Flesch–Kincaid readability tests: " + String.format("%.2f", result) + retAge(result));
        return result;
    }

    public static double sM (int po, int s) {
        double result = 1.043 * Math.sqrt((double)po * (30 / (double)s)) + 3.1291;
        System.out.println("Simple Measure of Gobbledygook: " + String.format("%.2f", result) + retAge(result));
        return result;
    }

    public static double cL (int c, int w, int s) {
        double bigL = ((double) 100 * c) / w;
        double bigS = ((double) 100 * s) / w;
        double result = 0.0588 * bigL - 0.296 * bigS - 15.8;
        System.out.println("Coleman–Liau index: " + String.format("%.2f", result) + retAge(result));
        return result;
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String retAge(double score) {
        String ageRange = "";
        switch ((int) Math.ceil(score)) {
            case 1:
                ageRange = " (about 6 year olds).";
                break;
            case 2:
                ageRange = " (about 7 year olds).";
                break;
            case 3:
                ageRange = " (about 9 year olds).";
                break;
            case 4:
                ageRange = " (about 10 year olds).";
                break;
            case 5:
                ageRange = " (about 11 year olds).";
                break;
            case 6:
                ageRange = " (about 12 year olds).";
                break;
            case 7:
                ageRange = " (about 13 year olds).";
                break;
            case 8:
                ageRange = " (about 14 year olds).";
                break;
            case 9:
                ageRange = " (about 15 year olds).";
                break;
            case 10:
                ageRange = " (about 16 year olds).";
                break;
            case 11:
                ageRange = " (about 17 year olds).";
                break;
            case 12:
                ageRange = " (about 18 year olds).";
                break;
            case 13:
                ageRange = " (about 24 year olds).";
                break;
            default:
                ageRange = " (about 24+ year olds).";
                break;
        }
        return ageRange;
    }
}
