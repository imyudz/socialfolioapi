package br.com.socialfolio.socialfolioapi.auth;

import br.com.socialfolio.socialfolioapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final UserRepository repository;
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @ModelAttribute RegisterRequest request
    ) {
        try{
            return ResponseEntity.ok(service.register(request));

        }catch(Exception e){
            var erro = AuthenticationResponse.builder().errorMessage("Erro ao registrar usuário: Email já foi registrado").build();
            System.out.println("Erro ao salvar dado: " + erro);
            return ResponseEntity.badRequest().body(erro);

        }

    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        System.out.print("teste");
        return ResponseEntity.ok(service.authenticate(request));
    }


}
