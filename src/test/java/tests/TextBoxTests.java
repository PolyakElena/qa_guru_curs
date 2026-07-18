package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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
        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format(".react-datepicker__day--0%s", day)).click();
        $("[id=subjectsInput]").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("[id=uploadPicture]").uploadFromClasspath("picture.jpg");
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id='react-select-3-input']").setValue(shtat).pressEnter();
        $("[id='react-select-4-input']").setValue(city).pressEnter();
        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text(genter));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text(hobby));
        $(".table-responsive").shouldHave(text(data));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text(cityAndShtat));
    }

    @Test
    void successfullCompletedWithOnlyRequiredFieldTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=submit]").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(genter));
        $(".table-responsive").shouldHave(text(userNumber));
    }

    @Test
    void shortEmailTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(shortEmail);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(String.format(".react-datepicker__day--0%s", day)).click();
        $("#submit").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void wrongEmailTest() {
        open("/automation-practice-form");
        $("[id=userEmail]").setValue(wrongEmail);
        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void wrongNumberTest() {
        open("/automation-practice-form");
        $("[id=userNumber]").setValue(wrongNumber);
        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void emptyNumberTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(emptyNumber);
        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}