package com.kanboard.tests.base;

import com.kanboard.utils.PowerShellUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Path;

public class SuiteDatabaseHooks {

    private static final String RESTORE_SCRIPT =
            Path.of(System.getProperty("user.dir"), "scripts", "restore-baseline.ps1").toString();

    @BeforeSuite(alwaysRun = true)
    public void restoreBaselineBeforeSuite() {
        PowerShellUtils.runScript(RESTORE_SCRIPT);
    }

    @AfterSuite(alwaysRun = true)
    public void restoreBaselineAfterSuite() {
        PowerShellUtils.runScript(RESTORE_SCRIPT);
    }
}