package co.com.smorales.jwe;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;

import java.time.Duration;

public class JweAuthConfigurationProperties {

    private String algorithm = JWEAlgorithm.RSA_OAEP_256.getName();

    private String encryptionMethod = EncryptionMethod.A128GCM.getName();

    private Duration expiration;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    public void setEncryptionMethod(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    public Duration getExpiration() {
        return expiration;
    }

    public void setExpiration(Duration expiration) {
        this.expiration = expiration;
    }
}
