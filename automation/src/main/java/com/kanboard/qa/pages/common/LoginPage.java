package com.kanboard.qa.pages.common;

import com.kanboard.qa.base.BasePage;

public class LoginPage extends BasePage {
    public DashboardPage login(String username, String password) {
        return new DashboardPage();
    }
}
