package com.kanboard.models;

import com.kanboard.utils.ConfigReader;

public final class TestUsers {

    private TestUsers() {
    }

    public static TestUser admin() {
        return new TestUser(
                "U_ADMIN",
                ConfigReader.getCredential("admin.username"),
                ConfigReader.getCredential("admin.password"),
                ApplicationRole.ADMINISTRATOR
        );
    }

    public static TestUser manager() {
        return new TestUser(
                "U_MANAGER",
                ConfigReader.getCredential("manager.username"),
                ConfigReader.getCredential("manager.password"),
                ApplicationRole.MANAGER
        );
    }

    public static TestUser standardUser() {
        return new TestUser(
                "U_USER",
                ConfigReader.getCredential("user.username"),
                ConfigReader.getCredential("user.password"),
                ApplicationRole.USER
        );
    }

    public static TestUser projectManagerUser() {
        return new TestUser(
                "U_PM",
                ConfigReader.getCredential("pm.username"),
                ConfigReader.getCredential("pm.password"),
                ApplicationRole.USER
        );
    }

    public static TestUser projectMemberUser() {
        return new TestUser(
                "U_MEM",
                ConfigReader.getCredential("member.username"),
                ConfigReader.getCredential("member.password"),
                ApplicationRole.USER
        );
    }

    public static TestUser projectViewerUser() {
        return new TestUser(
                "U_VIEW",
                ConfigReader.getCredential("viewer.username"),
                ConfigReader.getCredential("viewer.password"),
                ApplicationRole.USER
        );
    }

    public static TestUser customRoleUser() {
        return new TestUser(
                "U_CUST",
                ConfigReader.getCredential("custom.username"),
                ConfigReader.getCredential("custom.password"),
                ApplicationRole.USER
        );
    }
}