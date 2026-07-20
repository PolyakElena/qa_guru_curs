package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {
    CalendarComponent calendar = new CalendarComponent();

    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement genderContainer = $("#genterWrapper");
    private SelenideElement userNumberInput = $("#userNumber");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement genderHobbyContainer = $("#hobbiesWrapper");
    private SelenideElement uploadPicture = $("#uploadPicture");
    private SelenideElement addressInput = $("#currentAddress");
    private SelenideElement stateSelect = $("#state");
    private SelenideElement citySelect = $("#city");
    private SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private SelenideElement stateStateContainer = $("#stateCity-wrapper");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement modalWindow = $(".modal-content");
    private final SelenideElement table = $(".table-responsive");


    public RegistrationPage openPage() {
        open("/automation-practice-form");
//        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
//        executeJavaScript("$('#fixedban').remove()");
//        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationPage typeSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobby(String value) {
        genderHobbyContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPicture.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage typeAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        stateSelect.click();
        stateStateContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        citySelect.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    public RegistrationPage submitClick() {
        submitButton.click();

        return this;
    }

    public RegistrationPage checkModalWindow() {
        modalWindow.shouldBe(visible);
        modalWindow.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public RegistrationPage checkNotModalWindow() {
        modalWindow.shouldNotBe(visible);
        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        table.$(byText(key))
                .parent()
                .shouldHave(text(value));

        return this;
    }

    public RegistrationPage checkUserEmailBorderColor() {
        userEmailInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        return this;
    }

    public RegistrationPage checkUserNumberBorderColor() {
        userNumberInput.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        return this;
    }
}
