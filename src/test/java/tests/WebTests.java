package tests;

import com.codeborne.selenide.Configuration;
import data.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class WebTests {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @ValueSource(strings = {
            "ele",
            "ele@",
            "ele.ru"
    })
    @ParameterizedTest
    @DisplayName("Проверка валидации поля Email, вводимое значение {0}")
    void checkValidationEmailTest(String value) {
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=userEmail]").setValue(value);
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

    @CsvFileSource(resources = "/test_data/fullCompletedWithOnlyRequiredField.csv")
    @ParameterizedTest
    @DisplayName("Проверка регистрации только с обязательными полями. {0} {1} {2}")
    void successFullCompletedWithOnlyRequiredFieldTest(String name, String lastName, String phone) {
        open("/automation-practice-form");
        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(lastName);
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue(phone);
        $("[id=submit]").click();
        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text(name + " " + lastName));
        $(".table-responsive").shouldHave(text("Female"));
        $(".table-responsive").shouldHave(text(phone));
        closeWebDriver();
    }

    @EnumSource(Gender.class)
    @ParameterizedTest
    @DisplayName("Проверка выбора чекпокса Пол {0}")
    void checkCheckboxGenderTest(Gender gender) {
        open("/automation-practice-form");
        $("[id=firstName]").setValue("Елена");
        $("[id=lastName]").setValue("Полякова");
        $("[id=genterWrapper]").$(byText(String.valueOf(gender))).click();
        $("[id=userNumber]").setValue("9162223344");
        $("[id=submit]").click();
        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Елена Полякова"));
        $(".table-responsive").shouldHave(text(String.valueOf(gender)));
        $(".table-responsive").shouldHave(text("9162223344"));
        closeWebDriver();
    }


}
