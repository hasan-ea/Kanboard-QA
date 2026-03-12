package com.kanboard.qa.pages.common;

import com.kanboard.qa.base.BasePage;

public class DashboardPage extends BasePage {
    public SidebarMenu sidebar() {
        return new SidebarMenu();
    }
}
