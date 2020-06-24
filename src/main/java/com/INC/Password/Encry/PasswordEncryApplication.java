package com.INC.Password.Encry;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class PasswordEncryApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PasswordEncryApplication.class, args);
	}
	@Bean(name = "encryptorBean")
	public StringEncryptor stringEncryptor() {
	    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    SimpleStringPBEConfig config = new SimpleStringPBEConfig();
	    config.setPassword("password");//secret key for encryption
	    config.setAlgorithm("PBEWithMD5AndDES");//Algorithm use for encry
	    config.setKeyObtentionIterations("1000");
	    config.setPoolSize("1");
	    config.setProviderName("SunJCE");
	    config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
	    config.setStringOutputType("base64");
	    encryptor.setConfig(config);
	    return encryptor;
	}
	@Autowired
	ApplicationContext appCtx;
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Environment environment = appCtx.getBean(Environment.class);
		System.out.println(environment.getProperty("encryptedv3.property"));
		
	}

}
