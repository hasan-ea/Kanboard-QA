package com.kanboard.tests.base;

import com.kanboard.api.client.KanboardApiClient;
import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected KanboardApiClient apiClient;

    protected final TestUser adminUser = TestUsers.admin();
    protected final TestUser managerUser = TestUsers.manager();
    protected final TestUser standardUser = TestUsers.standardUser();

    @BeforeClass(alwaysRun = true)
    public void setUpApiClass() {
        apiClient = new KanboardApiClient();
    }
}