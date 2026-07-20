package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static tests.TestData.*;

public class RegistrationPageTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void successfulFillFormTest() {
        registrationPage
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
        registrationPage
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
        registrationPage
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
        registrationPage
                .openPage()
                .typeUserEmail(wrongEmail)
                .submitClick()

                .checkNotModalWindow()
                .checkUserEmailBorderColor();

    }

    @Test
    void wrongNumberTest() {
        registrationPage
                .openPage()
                .typeUserNumber(wrongNumber)
                .submitClick()

                .checkNotModalWindow()
                .checkUserNumberBorderColor();

    }

    @Test
    void emptyNumberTest() {
        registrationPage
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