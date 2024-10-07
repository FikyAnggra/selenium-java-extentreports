package testcases.featureA;

import objectrepository.featureA.page_a;
import org.testng.annotations.Test;
import profile.GlobalVariable;
import utils.WebUI;

public class scenario_a {
    @Test
    public void failedLoginWithWrongUsernameAndCorrectPassword() {
        WebUI.openBrowser("chrome", "https://practicetestautomation.com/practice-test-login/");
        WebUI.waitForElementPresent(page_a.textField_username(), GlobalVariable.shortTime);
        WebUI.setText(page_a.textField_username(), "fiky");
        WebUI.setText(page_a.textField_password(), "Password123");
        WebUI.click(page_a.button_masuk());
        WebUI.verifyElementPresent(page_a.failedUsername(), GlobalVariable.shortTime);
        WebUI.closeBrowser();
    }

    @Test
    public void failedLoginWithCorrectUsernameAndWrongPassword() {
        WebUI.openBrowser("chrome", "https://practicetestautomation.com/practice-test-login/");
        WebUI.waitForElementPresent(page_a.textField_username(), GlobalVariable.shortTime);
        WebUI.setText(page_a.textField_username(), "student");
        WebUI.setText(page_a.textField_password(), "password");
        WebUI.click(page_a.button_masuk());
        WebUI.verifyElementPresent(page_a.failedPassword(), GlobalVariable.shortTime);
        WebUI.closeBrowser();
    }
}
