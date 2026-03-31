package com.kanboard.tests.base;

import com.kanboard.api.client.KanboardApiClient;
import com.kanboard.models.TestUser;
import com.kanboard.models.TestUsers;
import com.kanboard.utils.PowerShellUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseApiTest {

    protected KanboardApiClient apiClient;

    protected final TestUser adminUser = TestUsers.admin();
    protected final TestUser managerUser = TestUsers.manager();
    protected final TestUser standardUser = TestUsers.standardUser();

    private static final String RESTORE_SCRIPT =
            System.getProperty("user.dir") + "\\scripts\\restore-baseline.ps1";

    @BeforeClass(alwaysRun = true)
    public void setUpApiClass() {
        restoreBaseline();
        apiClient = new KanboardApiClient();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownApiClass() {
        restoreBaseline();
    }

    protected void restoreBaseline() {
        PowerShellUtils.runScript(RESTORE_SCRIPT);
    }
}