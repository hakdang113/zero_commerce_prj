package com.commerce.zero_cms_security.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;


public class Aes256Util {
    // 암호화, 복호화를 위해 필요한 클래스

    public static String alg = "AES/CBC/PKCS5Padding";
    private static final String KEY = "ZEROCOMMERCEPROJECTKEYPROJECTKEY"; // 32Bytes(영문 기준)
        // 32Bytes 보다 크거나 작을 시 Invalid Aes Key 에러 발생

    private static final String IV = KEY.substring(0, 16); // 16Bytes


    // 암호화
    public static String encrypt (String text) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(encrypted);
        } catch (Exception e) {
            return null;
        }
    }

    // 복호화
    public static String decrypt (String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

            byte[] decodedBytes = Base64.decodeBase64(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}
