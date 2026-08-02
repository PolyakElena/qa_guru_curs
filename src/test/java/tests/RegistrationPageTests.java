package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationPageTests extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void successfulFillFormTest() {
        TestData testData = new TestData();
        registrationPage
                .openPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeUserEmail(testData.userEmail)
                .setGender(testData.genter)
                .typeUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .typeSubjects(testData.subject)
                .setHobby(testData.hobby)
                .uploadPicture(testData.picture)
                .typeAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .submitClick()

                .checkModalWindow()
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Student Email", testData.userEmail)
                .checkResult("Gender", testData.genter)
                .checkResult("Mobile", testData.userNumber)
                .checkResult("Date of Birth", testData.data)
                .checkResult("Subjects", testData.subject)
                .checkResult("Hobbies", testData.hobby)
                .checkResult("Picture", testData.picture)
                .checkResult("Address", testData.currentAddress)
                .checkResult("State and City", testData.state + " " + testData.city);

    }

    @Test
    void successFullCompletedWithOnlyRequiredFieldTest() {
        TestData testData = new TestData();
        registrationPage
                .openPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.genter)
                .typeUserNumber(testData.userNumber)
                .submitClick()

                .checkModalWindow()
                .checkResult("Student Name", testData.firstName + " " + testData.lastName)
                .checkResult("Gender", testData.genter)
                .checkResult("Mobile", testData.userNumber);

    }

    @Test
    void shortEmailTest() {
        TestData testData = new TestData();
        registrationPage
                .openPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeUserEmail(testData.shortEmail)
                .setGender(testData.genter)
                .typeUserNumber(testData.userNumber)
                .setDateOfBirth(testData.day, testData.month, testData.year)
                .submitClick()

                .checkNotModalWindow()
                .checkUserEmailBorderColor();


    }

    @Test
    void wrongEmailTest() {
        TestData testData = new TestData();
        registrationPage
                .openPage()
                .typeUserEmail(testData.wrongEmail)
                .submitClick()

                .checkNotModalWindow()
                .checkUserEmailBorderColor();

    }

    @Test
    void wrongNumberTest() {
        TestData testData = new TestData();
        registrationPage
                .openPage()
                .typeUserNumber(testData.wrongNumber)
                .submitClick()

                .checkNotModalWindow()
                .checkUserNumberBorderColor();

    }

    @Test
    void emptyNumberTest() {
        TestData testData = new TestData();
        registrationPage
                .openPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.genter)
                .typeUserNumber(testData.emptyNumber)
                .submitClick()

                .checkNotModalWindow()
                .checkUserNumberBorderColor();

    }
}