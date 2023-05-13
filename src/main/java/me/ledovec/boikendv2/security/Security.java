package me.ledovec.boikendv2.security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public final class Security {

    private final Charset charset = StandardCharsets.UTF_8;
    private final String salt;

    public Security(@Value("${security.salt}") String salt) {
        this.salt = salt;
    }

    public String sha256Hash(String password) {
        return sha256Hash(password, salt.getBytes(charset), charset);
    }

    @SneakyThrows
    public String sha256Hash(String password, byte[] salt, Charset charset) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashBytes = digest.digest(password.getBytes(charset));
        return new String(hashBytes, charset);
    }

    public boolean passwordMatches(String password, String secret) {
        return secret.equals(sha256Hash(password));
    }

}
