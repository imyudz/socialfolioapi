package br.com.socialfolio.socialfolioapi;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SocialfolioapiApplication implements CommandLineRunner{

	public static void main(String[] args) { 
		SpringApplication.run(SocialfolioapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		limparPasta("src/main/resources/static/uploads");
	}

	private void limparPasta(String caminhoPasta) throws IOException {
        Path pasta = Paths.get(caminhoPasta);
        
        if (!Files.exists(pasta)) {
            System.out.println("A pasta não existe.");
            return;
        }
        
        Files.walk(pasta)
            .filter(Files::isRegularFile)
            .map(Path::toFile)
            .forEach(File::delete);
        
        System.out.println("Arquivos deletados com sucesso!");
    }

}
