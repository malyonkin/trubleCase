import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Cookie;

@ExtendWith({TextReportExtension.class})
public class addCoockie {

    @BeforeEach
    void setUp() {
        System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        Configuration.baseUrl = "https://autospot.ru";
        WebDriverRunner.getWebDriver().manage().deleteCookieNamed("exp-16096");
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("exp-16096", "b"));
    }

    @RepeatedTest(1)
    @DisplayName("Футер. Отправка формы 'Задать вопрос'")
    void askQuestionFormInFooterTest() {
        open("/");
        element(by("data-telegram",""))
                .should(exist)
                .scrollTo()
                .shouldBe(Condition.visible)
                .shouldHave(exactText("Задать вопрос"));
        executeJavaScript("document.querySelector('[data-telegram]').click()");

        elements(".input-box").get(0).find("input[type=text]").should(Condition.visible).val("Иван Иванов");
        elements(".input-box").get(1).find("input").should(Condition.visible).val("1111111111");
        for (int i=1;i<3;i++) elements(".checkbox__tag").get(i).click();
        /*element(by("data-element-marker-id","popup_order-modal_button_send")).scrollTo().click();
        element("h2.as-c-popup__title").shouldBe(visible).shouldHave(text("Спасибо за обращение!")); //element(".site-popup__title").shouldBe(visible).shouldHave(text("Спасибо"))
        element(".button-link--big").click() //element(by("data-element-marker-id","mfp_button-close")).click();*/
    }
}
