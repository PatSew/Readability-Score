package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String s = readFileAsString(args[0]);

        int words = s.split(" ").length;
        System.out.println("Words: " + words);

        int sentences = s.split("[.!?]").length;
        System.out.println("Sentences: " + sentences);

        int characters = 0;
        char[] temp = s.toCharArray();
        for (char c : temp) {
            if (c != ' ' && c != '\t' && c != '\n') {
                characters++;
            }
        }
        System.out.println("Characters: " + characters);

        double score = 4.71 * ((double)characters / (double)words) + 0.5 * ((double)words / (double)sentences) - 21.43;
        System.out.println("The score is: " + String.format("%.2f", score));

        System.out.println("This text should be understood by " + retAge(score) + " year olds.");
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String retAge(double score) {
        String ageRange = "";
        switch ((int) Math.ceil(score)) {
            case 1:
                ageRange = "5-6";
                break;
            case 2:
                ageRange = "6-7";
                break;
            case 3:
                ageRange = "7-9";
                break;
            case 4:
                ageRange = "9-10";
                break;
            case 5:
                ageRange = "10-11";
                break;
            case 6:
                ageRange = "11-12";
                break;
            case 7:
                ageRange = "12-13";
                break;
            case 8:
                ageRange = "13-14";
                break;
            case 9:
                ageRange = "14-15";
                break;
            case 10:
                ageRange = "15-16";
                break;
            case 11:
                ageRange = "16-17";
                break;
            case 12:
                ageRange = "17-18";
                break;
            case 13:
                ageRange = "18-24";
                break;
            case 14:
                ageRange = "24+";
                break;
        }
        return ageRange;
    }
}
