package utils;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {


    public static String getRandomString(int length) {
//        String LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));

        return result.toString();
    }

    public static String getRandomEmail() {
        return format("%s@%s.com", getRandomString(8), getRandomString(8));
    }


    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // +3 (263) 253 - 66 - 12
    public static String getRandomPhone() {
        String phoneTemplate = "%s%s%s%s";

        return format(phoneTemplate, getRandomInt(111, 999), getRandomInt(111, 999)
                , getRandomInt(11, 99), getRandomInt(11, 99));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromStringArray(genders);
    }

    public static String getRandomMonth() {
        String[] month = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        return getRandomItemFromStringArray(month);
    }

    public static String getRandomHobby() {
        String[] hobby = {"Sports", "Reading", "Music"};

        return getRandomItemFromStringArray(hobby);
    }

    public static String getRandomSubject() {
        String[] subject = {"Maths",
                "Arts",
                "Accounting",
                "Biology",
                "Chemistry",
                "Commerce",
                "Civics",
                "Computer Science",
                "Economics",
                "English",
                "Hindi",
                "History",
                "Physics",
                "Social Studies"};

        return getRandomItemFromStringArray(subject);
    }

    public static String getRandomState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomItemFromStringArray(state);
    }

    public static String generateCity(String state) {
        switch (state) {
            case "NCR":
                return getRandomCityNCR();
            case "Uttar Pradesh":
                return getRandomCityUttaPradesh();
            case "Haryana":
                return getRandomCityHaryana();
            case "Rajasthan":
                return getRandomCityRajasthan();
            default:
                throw new IllegalArgumentException("Unknown state: " + state);
        }
    }

    public static String getRandomCityNCR() {
        String[] city = {"Delhi", "Gurgaon", "Noida"};

        return getRandomItemFromStringArray(city);
    }

    public static String getRandomCityUttaPradesh() {
        String[] city = {"Agra", "Lucknow", "Merrut"};

        return getRandomItemFromStringArray(city);
    }

    public static String getRandomCityHaryana() {
        String[] city = {"Karnal", "Panipat"};

        return getRandomItemFromStringArray(city);
    }

    public static String getRandomCityRajasthan() {
        String[] city = {"Jaipur", "Jaiselmer"};

        return getRandomItemFromStringArray(city);
    }


    public static String getRandomItemFromStringArray(String[] stringArray) {
        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength - 1);

        return stringArray[randomIndex];
    }
}