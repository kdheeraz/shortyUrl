package com.example.demo;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInit {

    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/java/com/example/demo/serviceaccount.json");

                    FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://insta-clone-bd038.firebaseio.com")
                    .build();
                  
                  FirebaseApp.initializeApp(options);  
                  System.out.println(options);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}