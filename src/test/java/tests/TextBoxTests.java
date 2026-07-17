package tests;

import com.codeborne.selenide.Configuration;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }


    @Test
    void successfulFillFormTest() {
        String firstName = "Елена";
        String lastName = "Полякова";
        String name = firstName + " " + lastName;
        String userEmail = "elena@yandex.ru";
        String userNumber = "9162223344";
        String genter = "Female";
        String hobby = "Music";
        String currentAddress = "My address";
        String month = "May";
        String year = "1999";
        String data = "20 May,1999";

        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day.react-datepicker__day--020").click();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("[id=uploadPicture]").uploadFromClasspath("picture.jpg");
        $("[id=currentAddress]").setValue(currentAddress);
        $("[id='react-select-3-input']").setValue("NCR").pressEnter();
        $("[id='react-select-4-input']").setValue("Delhi").pressEnter();
        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text(name));
        $(".table-responsive").shouldHave(text(userEmail));
        $(".table-responsive").shouldHave(text(genter));
        $(".table-responsive").shouldHave(text(userNumber));
        $(".table-responsive").shouldHave(text("Subjects"));
        $(".table-responsive").shouldHave(text(hobby));
        $(".table-responsive").shouldHave(text(data));
        $(".table-responsive").shouldHave(text(currentAddress));
        $(".table-responsive").shouldHave(text("NCR Delhi"));
        closeWebDriver();
    }

    @Test
    void successfullCompletedWithOnlyRequiredFieldTest() {
        String firstName = "Елена";
        String lastName = "Полякова";
        String name = firstName + " " + lastName;
        String userNumber = "9162223344";
        String genter = "Female";

        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=submit]").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text(name));
        $(".table-responsive").shouldHave(text(genter));
        $(".table-responsive").shouldHave(text(userNumber));
        closeWebDriver();
    }

    @Test
    void shortEmailTest() {
        String firstName = "Елена";
        String lastName = "Полякова";
        String userNumber = "9162223344";
        String genter = "Female";
        String month = "May";
        String year = "1999";

        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue("ele");
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(userNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day.react-datepicker__day--020").click();
        $("#submit").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();
    }

    @Test
    void wrongEmailTest() {
        String wrongEmail = "Елена@yandex.ru";

        open("/automation-practice-form");
        $("[id=userEmail]").setValue(wrongEmail);
        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();

    }

    @Test
    void wrongNumberTest() {
        String wrongNumber = "вв";

        open("/automation-practice-form");
        $("[id=userNumber]").setValue(wrongNumber);
        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();
    }

    @Test
    void emptyNumberTest() {
        String firstName = "Елена";
        String lastName = "Полякова";
        String genter = "Female";
        String emptyNumber = "";

        open("/automation-practice-form");
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=genterWrapper]").$(byText(genter)).click();
        $("[id=userNumber]").setValue(emptyNumber);
        $("[id=submit]").click();

        $(".modal-content").shouldNotBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();
    }
}