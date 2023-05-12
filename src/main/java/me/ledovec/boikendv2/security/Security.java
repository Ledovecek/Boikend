package me.ledovec.boikendv2.security;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public final class Security {

    public static String sha256Hash(String password) {
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    public static boolean passwordMatches(String password, String secret) {
        return secret.equals(sha256Hash(password));
    }

}
