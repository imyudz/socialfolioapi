package br.com.socialfolio.socialfolioapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.socialfolio.socialfolioapi.firebase.FirebaseConfig;


@SpringBootApplication
public class SocialfolioapiApplication implements CommandLineRunner{

	public static void main(String[] args) { 
		SpringApplication.run(SocialfolioapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        FirebaseConfig firebaseConfig = new FirebaseConfig();
        firebaseConfig.initializeFirebase();
	}

}
