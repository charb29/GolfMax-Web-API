package com.Rest.GolfMax.API.Security.GolfMax;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordSecurity {

    public String getEncrypted(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return generateHash(password);
    }

    public boolean isValid(String password, String encryptedPassword) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        String[] encryptedPassArr = encryptedPassword.split(":");
        int iterations = Integer.parseInt(encryptedPassArr[0]);
        byte[] salt = fromHex(encryptedPassArr[1]);
        byte[] hash = fromHex(encryptedPassArr[2]);

        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] encodedHash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        int difference = hash.length ^ encodedHash.length;

        for (int i = 0; i < hash.length && i < encodedHash.length; i++) {
            difference |= hash[i] ^ encodedHash[i];
        }
        return difference == 0;
    }

    private String generateHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] passwordCharArr = password.toCharArray();
        byte[] salt = getSalt();
        int keyLength = 64 * 8;

        PBEKeySpec pbeKeySpec = new PBEKeySpec(passwordCharArr, salt, iterations, keyLength);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    private String toHex(byte[] bytes) {
        BigInteger bigInteger = new BigInteger(1, bytes);
        String hex = bigInteger.toString(16);

        int paddingLength = (bytes.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("0%" + paddingLength + "d", 0) + hex;
        }
        else {
            return hex;
        }
    }

    private byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
