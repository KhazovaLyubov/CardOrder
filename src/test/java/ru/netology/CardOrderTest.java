package ru.netology;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardOrderTest {
    @Test
    public void ValidNumberAndName(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void NotValidName(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Khazova Lyubov");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void NotValidNumber(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("11111111111+");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void NotСonsentСheckbox(){
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Хазова Любовь");
        $("[data-test-id=phone] input").setValue("+11111111111");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));

    }
}
