package tests;

import com.codeborne.selenide.Configuration;
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
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=userEmail]").setValue("elena@yandex.ru");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day.react-datepicker__day--020").click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("[id=uploadPicture]").uploadFromClasspath("picture.jpg");
        $("[id=currentAddress]").setValue("My address");
        $("[id='react-select-3-input']").setValue("NCR").pressEnter();
        $("[id='react-select-4-input']").setValue("Delhi").pressEnter();
        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text("Елена Полякова"));
        $(".table-responsive").shouldHave(text("elena@yandex.ru"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("9162223344"));
        $(".table-responsive").shouldHave(text("Subjects"));
        $(".table-responsive").shouldHave(text("Music"));
        $(".table-responsive").shouldHave(text("20 May,1999"));
        $(".table-responsive").shouldHave(text("My address"));
        $(".table-responsive").shouldHave(text("NCR Delhi"));
        closeWebDriver();
    }

    @Test
    void successfullCompletedWithOnlyRequiredFieldTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=submit]").click();
        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Елена Полякова"));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text("9162223344"));
        closeWebDriver();
    }

    @Test
    void shortEmailTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=userEmail]").setValue("ele");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day.react-datepicker__day--020").click();
        $("#submit").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();
    }

    @Test
    void wrongEmailTest() {
        open("/automation-practice-form");
        $("[id=userEmail]").setValue("Елена@yandex.ru");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();

    }

    @Test
    void wrongNumberTest() {
        open("/automation-practice-form");
        $("[id=userNumber]").setValue("вв");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();
    }

    @Test
    void emptyNumberTest() {
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue("");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        $("[id=userNumber]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        closeWebDriver();
    }
}