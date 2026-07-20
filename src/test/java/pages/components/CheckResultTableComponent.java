package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CheckResultTableComponent {

    private final SelenideElement table = $(".table-responsive");

    public CheckResultTableComponent checkResult(String key, String value) {
        table.$(byText(key))
                .parent()
                .shouldHave(text(value));

        return this;
    }
}
