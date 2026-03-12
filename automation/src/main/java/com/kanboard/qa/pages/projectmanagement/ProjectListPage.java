package com.kanboard.qa.pages.projectmanagement;

import com.kanboard.qa.base.BasePage;
import com.kanboard.qa.pages.projectmanagement.create.CreateProjectPage;

public class ProjectListPage extends BasePage {
    public CreateProjectPage openCreateProject() {
        return new CreateProjectPage();
    }
}
