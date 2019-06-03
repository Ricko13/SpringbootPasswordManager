package com.ricko.passwordmanager.Service;
import org.springframework.stereotype.Component;

import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

@Component
public class EncryptionService {


    public void EncryptionService() { }

    public String encode(String str) {
        String encode1 = rot13(str);
        String encode2 = encryptAES(encode1);
        return encode2;
    }

    public String decode(String str) {

        String decode1 = decryptAES(str);
        String decode2 = rot13(decode1);
        return decode2;
    }


    private String rot13(String str) {

        char[] string = str.toCharArray();
        for (int i = 0; i < string.length; i++) {
            char litera = string[i];

            if (litera >= 'a' && litera <= 'z') {
                // Zmienia kolejność znaków małych

                if (litera > 'm') {
                    litera -= 13;
                } else {
                    litera += 13;
                }
            } else if (litera >= 'A' && litera <= 'Z') {
                // Zmienia kolejność znaków dużych

                if (litera > 'M') {
                    litera -= 13;
                } else {
                    litera += 13;
                }
            }
            string[i] = litera;
        }
        // Konwertuje tablicę na stringa
        return new String(string);
    }

    private static String secretKey = "testing_my_dudes";
    private static String salt = "itsamesecreto";

    public static String encryptAES(String strToEncrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Ups, błąd przy szyfrowaniu AES: " + e.toString());
        }
        return null;
    }


    public static String decryptAES(String strToDecrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Ups, błąd przy deszyfrowaniu AES: " + e.toString());
        }
        return null;
    }
}