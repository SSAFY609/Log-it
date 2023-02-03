package com.ssafy.logit.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptConfigTest {

    @Test
    public void jasyt_test() {
        String[] plainTexts = {"SSAFY", "TEST_VALUE"};

        for(int i = 0; i < plainTexts.length; i++) {
            String plainText = plainTexts[i];

            PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setPassword("mustChange");
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setKeyObtentionIterations("10");
            config.setPoolSize("1");
            config.setProviderName("SunJCE");
            config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
            config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
            config.setStringOutputType("base64");
            encryptor.setConfig(config);

            String encryptText = encryptor.encrypt(plainText);
            String decryptText = encryptor.decrypt(encryptText);
            Assertions.assertThat(plainText).isEqualTo(decryptText);

            System.out.println(i + "번 암호화 결과 : " + encryptText);
        }
    }
}