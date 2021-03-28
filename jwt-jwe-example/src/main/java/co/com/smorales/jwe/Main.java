package co.com.smorales.jwe;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.RSAKey;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Duration;
import java.util.UUID;

public class Main {

    private static final RSAPublicKey PUBLIC_KEY = KeyUtil.getRsaPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1UsJS0IcPgcw1FNZkmXE9bI339HoTuE2fuFhNNqhAtdo/aruEMUq7aw20Fn41Dk7qmLsYXEqAfq1avFO6Bar9j8KawS8E7F7cGJZDpr8xtiK8sYglAKmtZyylSRjGwMj6gyskXaGv4v7NDcIho9K2TccWyFWJMZdG4t1oT4hCG2FAt3s0lWfITVyVNQxJ18Nv90JFccoFgfxxKx7yzMXfTjMwbanHZ1F63J5w1tCTJaryJFwSD0N2nbQE5oD0op3XxJuF+csuFOKK4h6LQwvp7Ul5PstqN1W91SmMZWS1uhFdL/sPC8c+UewDbfV8UGp398ZYVbHI62gqoAsEdjHkQIDAQAB");
    private static final RSAPrivateKey PRIVATE_KEY = KeyUtil.getRsaPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDVSwlLQhw+BzDUU1mSZcT1sjff0ehO4TZ+4WE02qEC12j9qu4QxSrtrDbQWfjUOTuqYuxhcSoB+rVq8U7oFqv2PwprBLwTsXtwYlkOmvzG2IryxiCUAqa1nLKVJGMbAyPqDKyRdoa/i/s0NwiGj0rZNxxbIVYkxl0bi3WhPiEIbYUC3ezSVZ8hNXJU1DEnXw2/3QkVxygWB/HErHvLMxd9OMzBtqcdnUXrcnnDW0JMlqvIkXBIPQ3adtATmgPSindfEm4X5yy4U4oriHotDC+ntSXk+y2o3Vb3VKYxlZLW6EV0v+w8Lxz5R7ANt9XxQanf3xlhVscjraCqgCwR2MeRAgMBAAECggEBAMvB3gQOCMrctbHwWf43yFtqwGPJs+I+1X+KMQF75hq/y6Wg98H/iQ352XdarBGZBz2zONuxbo7rgQnDsjyXA/m+qWYo5L7vatumqCsCtn4C+WIJEbAzhCy0oAlH9IEmk9WP+/8OOpWb1QNBm5xoIPm24xjPlOqWsPWSWXIK1yWjMvj0MUmzBxjoE3NGKtk57GlEV6d3KlJb9gefUwz9ZMRLIKZn83BB9Od6AgNQUef2Qm/6EJdQ2Gz2MA2PFmydIU+hZ8Nen8NppoymqhFz6Mwwj8k/2obt9tZj/DKQGOJipAJRPvu++NWCb20JIQT/PjFYyh2j1WGNjYNzeP5FA40CgYEA+Noom+A1744O2M8qGg3A3bsD6Vzb8q/vMdLIH0yh4Shn21ubjWi2YTtZuIF639ZfYSG1HEac9ayei5DJmoZ8hsn54POUIqxLCblhAPnE71mXfjJP4nT6F+wwNyDGhJvmsq1lImd8jzJWTQVlm+FIx403w9IWkj1nscNpXorsj5sCgYEA22toSFSpzQH4WxHEcopvMbYEb1cMn/CMjo0ts/PmNhVKOAWZUwqwAYnAG4Q/brk50Blz6+Df8fr90ii4eXER5OavU9buJ6fByWeajBZAJ996cmPx8LjhYOg8Oa+EnhuvtLSQjKF9ucTJcylkCL+gWjSxkSzpG1VgKv+rCRJ1tkMCgYBRUrHcprvZiloNvj2q//2cOGaB16fFOnt3k8N0fw2T0CmIhPpjjumzArtCUYX/KkZHLsXJ9MWMf5ncUDeh+Psu7UhsnA03+4gWKG+r6vPRqDyasARtI/8q3DOxgFN/uJb+oy2evaT2AuQzDWV0OH4ZQGwN4VUg+1IsxIhlrg66qwKBgEPicx0NtIsMtP3X/WNCmSxUGAMqd2l0Tsg7vYwTRYIuDAxRDyK2B8YyDBF3VPi4rb5IYaj0pdSDxsuidZB8/wBGrq/Nq1jxee8J8rKsn6S2a3fAnDXRvUjyEM/4EZupDS9nPeGEhRVYYMsUXzKltsKx0s4LiwM94sbq2suvvmNpAoGAcEJhdwW7y0DmXsTKi7ypwEYO/gCVsOZk453NYD+Wik15vMy7nKqACwLZqC0vNYcdR+eAkOCrU8ORhTk+OeO1uzDieB0P44suPQmGYSUHrjvEv/9NEuABDTib7vs0I0Foaf+Vs19gF5G4H/UIlS6nu5bV2wu38DZwYL7z1NcuVxs=");

    private static final RSAKey RSA_JWK = new RSAKey.Builder(PUBLIC_KEY)
            .privateKey(PRIVATE_KEY)
            .keyUse(KeyUse.ENCRYPTION)
            .keyID(UUID.randomUUID().toString())
            .build();

    public static void main(String[] args) throws Exception {
        String jwe = "eyJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.eIqiiLxgr5vtZbBascbdm1Q11YzbJffn8Ud_bnMRTqaoqTamhGo1t4mPyoMZ6eldMmhxZ712bPvDyWATBaVyxh_eqvYhJdrcRXo_cCqeGg50OHJQFeyZTjtIAheMmYZJUnzg-Bwg-RwVmZa031GkFkohch_MNREs9uaIHgyRbT9iCQo7aqmtRbixBWW4MxIvi-KOcjTsH_nI-NOgYoIEq1DUAbhrBiTXaH_EBvAb2WiFrpH2FZE6Lh5bbwybZiHM1ONmp6ePp4OiTn5Cp8yp7fqmSLlQy_JbI-C0iOco5SwzWqMhYr7TYwUFueDR7dLuXlrVOKvc16MuWDw1NSo_Hw.jbWzqkBHGW-v7E7p.tCCE8g-S3JbHacLatpDbl3CeKZ11WpTbUWyRZ1L8JiP7Yc0KHW4vCHtQwL1fe9sp3Lud8CqxhUC2cVy12SQS4y5d7isJR1FuSXrPL3S2mY6gpexpGcupanUgQraLxsVjsTtSSa_BAubQca6Q0v7FtFSbvx3MIm-F9s-HZw4SMlrea7X90M6dpyVDCIhujRekxaQEtmw1KzPpxdJ2_rocwDT79kgjZzdABrEXTGemGWrnnhmQwg.03WCn7m_Mi7XtFOD3BEPjA";

        JweAuthConfigurationProperties properties = new JweAuthConfigurationProperties();
        properties.setExpiration(Duration.ofMinutes(1));

        AuthorizationService authService = new JweAuthorizationService(RSA_JWK, properties);
        jwe = authService.obtainAccessToken(UUID.randomUUID().toString());
        System.out.println("JWE: " + jwe);

        try {
            String sessionId = authService.verify(jwe);
            System.out.println(sessionId);
        } catch (RejectedJwtException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void test() {
        JWK jwk = new OctetSequenceKey.Builder(new byte[0])
                .keyID(UUID.randomUUID().toString()) // give the key some ID (optional)
                .algorithm(EncryptionMethod.A128GCM) // indicate the intended key alg (optional)
                .build();
    }

}
