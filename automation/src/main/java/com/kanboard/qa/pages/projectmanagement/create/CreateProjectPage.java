package com.kanboard.qa.pages.projectmanagement.create;

import com.kanboard.qa.base.BasePage;
import com.kanboard.qa.models.ProjectType;

public class CreateProjectPage extends BasePage {
    public CreateProjectPage enterProjectName(String projectName) {
        return this;
    }

    public CreateProjectPage selectProjectType(ProjectType projectType) {
        return this;
    }

    public void submit() {
    }

    public String getValidationMessage() {
        return "";
    }
}
