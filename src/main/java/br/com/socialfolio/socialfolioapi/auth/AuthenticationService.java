package br.com.socialfolio.socialfolioapi.auth;

import br.com.socialfolio.socialfolioapi.config.JwtService;
import br.com.socialfolio.socialfolioapi.firebase.DefaultFolder;
import br.com.socialfolio.socialfolioapi.firebase.FirebaseStorageService;
import br.com.socialfolio.socialfolioapi.user.Role;
import br.com.socialfolio.socialfolioapi.user.User;
import br.com.socialfolio.socialfolioapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import java.sql.Date;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final FirebaseStorageService firebaseStorageService;

        public AuthenticationResponse register(RegisterRequest request) {
                //Criando os nomes únicos das imagens e definindo caminhos padrões
                String coverImgName = UUID.randomUUID().toString() + "_" + request.getCoverImg().getOriginalFilename();
                String avatarImgName = UUID.randomUUID().toString() + "_" + request.getAvatar().getOriginalFilename();
                final String fireStoreCoverPath = DefaultFolder.COVER.getPath();
                final String fireStoreAvatarPath = DefaultFolder.AVATAR.getPath();      

                
                
                try {
                        //Faz upload das imagens e obtém link
                        String coverURl = firebaseStorageService.uploadFiletoFirebase(
                                        request.getCoverImg(),
                                        coverImgName,
                                        fireStoreCoverPath
                        );
                        String avatarURL = firebaseStorageService.uploadFiletoFirebase(
                                        request.getAvatar(),
                                        avatarImgName,
                                        fireStoreAvatarPath
                        );
                        var user = User.builder()
                                .firstName(request.getFirstName())
                                .lastName(request.getLastName())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .socialName(request.getSocialName())
                                .dtNasc(Date.valueOf(request.getDtNasc()))
                                .phone(request.getPhone())
                                .avatar(avatarURL)
                                .description(request.getDescription())
                                .state(request.getState())
                                .city(request.getCity())
                                .coverImg(coverURl)
                                .employee(request.getEmployee())
                                .workplace(request.getWorkplace())
                                .recent_Education(request.getRecent_Education())
                                .current_Company(request.getCurrent_Company())
                                .profission(request.getProfission())
                                .role(Role.USER)
                                .build();
                        repository.save(user);

                        var jwtToken = jwtService.generateToken(user);
                        return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        } catch (Exception e) {
                System.err.println("Erro ao gravar imagem " + e.getMessage());

                throw new RuntimeException("Erro ao gravar imagem " + e.getMessage());
        }
}

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .build();
        }
}
