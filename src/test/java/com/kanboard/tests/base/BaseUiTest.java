package com.kanboard.tests.base;

import com.kanboard.models.TestUser;
import com.kanboard.pages.DashboardPage;
import com.kanboard.pages.LoginPage;

public class BaseUiTest extends BaseTest {

    protected DashboardPage loginAs(TestUser user) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(user.getUsername(), user.getPassword());
        return new DashboardPage(driver);
    }
}