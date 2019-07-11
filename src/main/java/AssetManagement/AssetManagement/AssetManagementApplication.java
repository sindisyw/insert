package AssetManagement.AssetManagement;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
public class AssetManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssetManagementApplication.class, args);
        System.out.println("\n");
        //String token = UUID.randomUUID().toString();
        //System.out.println("Token : "+token);
//        int length = 5;
//        boolean useLetters = true;
//        boolean useNumbers = true;
//        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
//
//        System.out.println(generatedString.toUpperCase()+"\n");
        System.out.println("-----RUNN SPRING RUNN-------");
    }

}
