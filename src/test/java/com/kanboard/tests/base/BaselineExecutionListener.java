package com.kanboard.tests.base;

import com.kanboard.utils.PowerShellUtils;
import org.testng.IExecutionListener;

import java.nio.file.Path;

public class BaselineExecutionListener implements IExecutionListener {

    private static final String RESTORE_SCRIPT =
            Path.of(System.getProperty("user.dir"), "scripts", "restore-baseline.ps1").toString();

    @Override
    public void onExecutionStart() {
        PowerShellUtils.runScript(RESTORE_SCRIPT);
    }

    @Override
    public void onExecutionFinish() {
        PowerShellUtils.runScript(RESTORE_SCRIPT);
    }
}