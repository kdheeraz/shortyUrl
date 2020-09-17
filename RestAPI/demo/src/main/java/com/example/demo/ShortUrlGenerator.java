package com.example.demo;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ShortUrlGenerator {

    public String randomUrlGen() throws InterruptedException, ExecutionException {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 7;
        Random random = new Random();
     
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
     
        System.out.println(generatedString);
        return generatedString;
        
    
    }
    
}
