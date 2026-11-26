package com.auth.authservice.utilities;

import java.util.UUID;

public class UserUtility {

    public static UUID parseUUID(String uuidString) {
        try {
            return UUID.fromString(uuidString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID string: " + uuidString);
        }
    }
}
