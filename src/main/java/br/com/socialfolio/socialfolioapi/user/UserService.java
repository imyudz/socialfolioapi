package br.com.socialfolio.socialfolioapi.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse extractUserDetails(Integer userId) {
        byte[] coverImgBytes = null;
        byte[] profileImgBytes = null;

        var coverImgPath = userRepository.findById(userId).get().getCoverImg();
        var profileImgPath = userRepository.findById(userId).get().getAvatar();

        if (coverImgPath != null) {
            try {
                coverImgBytes = Files.readAllBytes(Paths.get(coverImgPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (profileImgPath != null) {
            try {
                profileImgBytes = Files.readAllBytes(Paths.get(profileImgPath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        var user = userRepository.findById(userId).orElseThrow();

        return UserInfoResponse.builder()
            .userId(userId)
            .name(user.getFirstName() + " " + user.getLastName())
            .socialName(user.getSocialName())
            .email(user.getEmail())
            .dtNasc(user.getDtNasc().toString())
            .phone(user.getPhone())
            .avatar(profileImgBytes)
            .description(user.getDescription())
            .state(user.getState())
            .city(user.getCity())
            .coverImg(coverImgBytes)
            .employee(user.getEmployee())
            .workplace(user.getWorkplace())
            .recent_Education(user.getRecent_Education())
            .current_Company(user.getCurrent_Company())
            .profission(user.getProfission())
            .build();
    }
}