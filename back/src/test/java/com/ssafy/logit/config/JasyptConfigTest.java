package com.ssafy.logit.config;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class JasyptConfigTest {


    @Test
    public void jasyt_test() {
        String plainText = "SSAFY";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("wlsgh");
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


    }

}