package com.scorer.tools;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EncryptTool {
    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    private static final String PUBLIC_KEY_STORE = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJvQ07QWPx4kPch+6/V85yNDXlmxtacIu967u6hlCZRKk5FYsNx7VJFg0Ot7N8SsnYOWejC5V+CsoywwK7qazskCAwEAAQ==";
    private static final String PRIVATE_KEY_STORE = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAm9DTtBY/HiQ9yH7r9XznI0NeWbG1pwi73ru7qGUJlEqTkViw3HtUkWDQ63s3xKydg5Z6MLlX4KyjLDAruprOyQIDAQABAkBsqFXZseHWHSPQm2Hk0XNGDgbPubOZheetTXFx3vOtKrMhSGHX1TqbFRlHc4h+R+mTOoFqbAdONCdR/ZPeBbYBAiEA5g+KKDvWh0EQ++2MZiQxjcSiXEyvycdgJnvpbWpOSykCIQCtYkjDcS6KYpum5k/4v7ODqDYFPSvtbIdq5btpkKx6oQIgfoELAkatF2CS+0TLUBsU2JTIhqJwgy7L0z2NxOmQtckCIQCG6hecnRGN9TURJd3Mvb6+D8IZSYRI905YS/MNLdpIgQIhAL5XjNaWA7rT5Cs9MqlSt+PynZTuCW7N/aeunT+7t/Pm";

    static {
        publicKey = restorePublicKey(Base64.decodeBase64(PUBLIC_KEY_STORE));
        privateKey = restorePrivateKey(Base64.decodeBase64(PRIVATE_KEY_STORE));
    }

    public static String decodeScorerSC(String encode) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(Base64.decodeBase64(encode)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encodeScorerSC(String encode) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64String(cipher.doFinal(encode.getBytes()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

}
