package com.trello.properties;

import java.util.ResourceBundle;

public class TrelloProperties {


    private static final String token = "trello.token";
    private static final String key = "trello.key";
    private static final String memberId = "trello.memberId";
    private static final String organizationId = "trello.organizationId";

    public static String getToken() {
        if (getProperty(token).isEmpty() || getProperty(token).startsWith("YOUR")) {
            return System.getProperty("token");
        } else {
            return getProperty(token);
        }
    }

    public static String getKey() {
        if (getProperty(key).isEmpty() || getProperty(key).startsWith("YOUR")) {
            return System.getProperty("key");
        } else {
            return getProperty(key);
        }
    }

    public static String getOrganizationId() {
        if (getProperty(organizationId).isEmpty() || getProperty(organizationId).startsWith("YOUR")) {
            return System.getProperty("organizationId");
        } else {
            return getProperty(organizationId);
        }
    }

    public static String getMemberId() {
        if (getProperty(memberId).isEmpty() || getProperty(memberId).startsWith("YOUR")) {
            return System.getProperty("memberId");
        } else {
            return getProperty(memberId);
        }
    }

    private static String getProperty(String key) {
        return ResourceBundle.getBundle("trello").getString(key);
    }
}
