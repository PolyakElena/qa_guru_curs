package tests;

import com.github.javafaker.Faker;
import utils.RandomUtils;

import java.util.Locale;

import static utils.RandomUtils.*;

public class TestData {
    public static Faker faker = new Faker();
    public static Faker fakerRu = new Faker(new Locale("ru"));

    public static String firstName = fakerRu.name().firstName();
    public static String lastName = fakerRu.name().lastName();
    public static String userEmail = faker.internet().emailAddress();
    public static String userNumber = getRandomPhone();
    public static String genter = getRandomGender();
    public static String hobby = getRandomHobby();
    public static String currentAddress = fakerRu.address().fullAddress();
    public static String day = String.valueOf(getRandomInt(1, 30));
    public static String month = getRandomMonth();
    public static String year = String.valueOf(getRandomInt(1900, 2026));
    public static String data = String.format("%s %s,%s", day, month, year);
    public static String subject = getRandomSubject();
    public static String state = getRandomState();
    public static String city = generateCity(state);
    public static String picture = "picture.jpg";

    public static String wrongEmail = fakerRu.internet().emailAddress();
    public static String shortEmail = RandomUtils.getRandomString(3);
    public static String wrongNumber = RandomUtils.getRandomString(2);
    public static String emptyNumber = "";

}
