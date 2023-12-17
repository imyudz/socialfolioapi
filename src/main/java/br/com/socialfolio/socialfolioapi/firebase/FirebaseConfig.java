package br.com.socialfolio.socialfolioapi.firebase;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {

    public FirebaseApp initializeFirebase() throws IOException {
        String JSONCredentials = System.getenv("FIREBASE_CREDENTIALS");
        if (JSONCredentials == null) {
            throw new IllegalArgumentException("Variável FIREBASE_CREDENTIALS não definida.");
        }

        FirebaseOptions options;
        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(JSONCredentials.getBytes())))
                    .setStorageBucket("socialfolio-408417.appspot.com")
                    .build();
            return FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao inicializar Firebase: " + e);
            throw e;
        }
    }
}                   