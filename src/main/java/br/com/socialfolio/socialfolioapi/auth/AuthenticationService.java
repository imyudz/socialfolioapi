package br.com.socialfolio.socialfolioapi.auth;

import br.com.socialfolio.socialfolioapi.config.JwtService;
import br.com.socialfolio.socialfolioapi.user.Role;
import br.com.socialfolio.socialfolioapi.user.User;
import br.com.socialfolio.socialfolioapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .socialName(request.getSocialName())
                .dtNasc(request.getDtNasc())
                .phone(request.getPhone())
                .avatar(request.getAvatar())
                .description(request.getDescription())
                .state(request.getState())
                .city(request.getCity())
                .coverImg(request.getCoverImg())
                .employee(request.getEmployee())
                .workplace(request.getWorkplace())
                .job_Level(request.getJob_Level())
                .recent_Education(request.getRecent_Education())
                .current_Company(request.getCurrent_Company())
                .role(Role.USER)
                .build();
        repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
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
                .build();
    }
}
