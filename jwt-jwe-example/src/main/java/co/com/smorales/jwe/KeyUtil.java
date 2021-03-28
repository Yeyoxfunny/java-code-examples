package co.com.smorales.jwe;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public final class KeyUtil {

    public static RSAPrivateKey getRsaPrivateKey(String rsaPrivateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(rsaPrivateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) fact.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAPublicKey getRsaPublicKey(String rsaPubKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(rsaPubKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            return (RSAPublicKey) fact.generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void generateRsaKeys() {
        try {
            KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
            rsaGen.initialize(2048);
            KeyPair rsaKeyPair = rsaGen.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) rsaKeyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) rsaKeyPair.getPrivate();

            System.out.println("Public RSA: " + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
            System.out.println("Private RSA: " + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
