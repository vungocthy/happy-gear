package com.notimplement.happygear.security.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class AppConfig {
    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        UUID uuid = UUID.randomUUID();

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("gadgetzone-49cd4-firebase-adminsdk-uluaa-d0ceb3a938.json")
                .getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "GadgetZone" + uuid);
        return FirebaseMessaging.getInstance(app);
    }
}
