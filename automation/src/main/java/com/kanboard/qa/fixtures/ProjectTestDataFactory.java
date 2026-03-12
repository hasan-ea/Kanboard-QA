package com.kanboard.qa.fixtures;

import com.kanboard.qa.models.ProjectData;
import com.kanboard.qa.models.ProjectType;

public class ProjectTestDataFactory {
    public ProjectData privateProject(String projectName) {
        ProjectData projectData = new ProjectData();
        projectData.setName(projectName);
        projectData.setType(ProjectType.PRIVATE);
        return projectData;
    }
}
