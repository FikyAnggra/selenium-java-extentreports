package objectrepository.featureB;

import org.openqa.selenium.By;

public class page_a {

    public static By textField_username () {
        return By.id("username");
    }

    public static By textField_password () {
        return By.id("password");
    }

    public static By button_masuk () {
        return By.id("submit");
    }

    public static By failedUsername () {
        return By.xpath("//div[text()='Your username is invalid!']");
    }

    public static By failedPassword () {
        return By.xpath("//div[text()='Your password is invalid!']");
    }
}
