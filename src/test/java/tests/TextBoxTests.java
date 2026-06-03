package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    @Test
    void successfulFillFormTest() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=userEmail]").setValue("elena@yandex.ru");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1999");
        $(".react-datepicker__day.react-datepicker__day--020").click();
//        $("input#subjectsInput").setValue("Subjects");
        $("[id=hobbies-checkbox-3]").click();
        $("[id=uploadPicture]").uploadFile(new File("picture.jpg"));
        $("[id=currentAddress]").setValue("My address");
        $("[class=css-1xc3v61-indicatorContainer]").click();
        $(byText("NCR")).click();
        $("[id=react-select-4-input]").click();
        $(byText("Delhi")).click();
        closeWebDriver();
    }

    @Test
    void successfulCompletedWithOnlyRequiredFieldTest() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=submit]").click();
        $(".modal-content").shouldBe(visible);
        closeWebDriver();
    }

    @Test
    void shortEmailTest() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=userEmail]").setValue("e");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        closeWebDriver();
    }

    @Test
    void wrongEmailTest() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("[id=userEmail]").setValue("Елена@yandex.ru");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        closeWebDriver();
    }

    @Test
    void wrongNumberTest() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("[id=userNumber]").setValue("вв");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        closeWebDriver();
    }

    @Test
    void emptyNumberTest() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("");
        $("[id=submit]").click();
        $(".modal-content").shouldNotBe(visible);
        closeWebDriver();
    }
}