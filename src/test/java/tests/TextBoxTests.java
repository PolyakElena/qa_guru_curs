package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.*;

public class TextBoxTests {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }


    @Test
    void successfulFillFormTest() {
        RegistrationPage registrationPage = new RegistrationPage()
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .setGender(genter)
                .typeUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .typeSubjects(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .typeAddress(currentAddress)
                .setStateAndCity(state, city)
                .submitClick()

                .checkModalWindow()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", genter)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", data)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", picture)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);

    }

    @Test
    void successFullCompletedWithOnlyRequiredFieldTest() {

        RegistrationPage registrationPage = new RegistrationPage()
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(genter)
                .typeUserNumber(userNumber)
                .submitClick()

                .checkModalWindow()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", genter)
                .checkResult("Mobile", userNumber);

    }

    @Test
    void shortEmailTest() {
        RegistrationPage registrationPage = new RegistrationPage()
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(shortEmail)
                .setGender(genter)
                .typeUserNumber(userNumber)
                .setDateOfBirth(day, month, year)
                .submitClick()

                .checkNotModalWindow()
                .checkUserEmailBorderColor();


    }

    @Test
    void wrongEmailTest() {
        RegistrationPage registrationPage = new RegistrationPage()
                .openPage()
                .typeUserEmail(wrongEmail)
                .submitClick()

                .checkNotModalWindow()
                .checkUserEmailBorderColor();

    }

    @Test
    void wrongNumberTest() {
        RegistrationPage registrationPage = new RegistrationPage()
                .openPage()
                .typeUserNumber(wrongNumber)
                .submitClick()

                .checkNotModalWindow()
                .checkUserNumberBorderColor();

    }

    @Test
    void emptyNumberTest() {
        RegistrationPage registrationPage = new RegistrationPage()
                .openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(genter)
                .typeUserNumber(emptyNumber)
                .submitClick()

                .checkNotModalWindow()
                .checkUserNumberBorderColor();

    }
}