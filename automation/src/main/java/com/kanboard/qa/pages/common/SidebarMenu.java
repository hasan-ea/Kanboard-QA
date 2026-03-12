package com.kanboard.qa.pages.common;

import com.kanboard.qa.pages.projectmanagement.ProjectListPage;

public class SidebarMenu {
    public ProjectListPage openProjectList() {
        return new ProjectListPage();
    }
}
