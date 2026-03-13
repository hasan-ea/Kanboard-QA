package com.kanboard.models;

public class TestUser {

    private final String alias;
    private final String username;
    private final String password;
    private final ApplicationRole applicationRole;

    public TestUser(String alias, String username, String password, ApplicationRole applicationRole) {
        this.alias = alias;
        this.username = username;
        this.password = password;
        this.applicationRole = applicationRole;
    }

    public String getAlias() {
        return alias;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationRole getApplicationRole() {
        return applicationRole;
    }
}