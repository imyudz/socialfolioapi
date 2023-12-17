package br.com.socialfolio.socialfolioapi.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import br.com.socialfolio.socialfolioapi.firebase.FirebaseStorageService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FirebaseStorageService firebaseStorageService;

    public List<UserInfoResponse> extractUserDetails() {
        var users = userRepository.findAll();
        List<UserInfoResponse> userInfos = new ArrayList<>();

        for (User user : users) {
            byte[] profileImgBytes = null;
            String profileImgPath = user.getAvatar();
            if (profileImgPath != null) {
                try {
                    profileImgBytes = firebaseStorageService.getFileFromFirebase(profileImgPath);
                } catch (Exception e) {  
                    e.printStackTrace();
                    System.err.println("Erro ao buscar imagem de avatar: " + e);
                    throw new RuntimeException(e);
                }
            }

            UserInfoResponse userInfo = UserInfoResponse.builder()
                .userId(user.getId())
                .name(user.getFirstName() + " " + user.getLastName())
                .avatar(profileImgBytes)
                .build();

            userInfos.add(userInfo);
        }

    return userInfos;
    }
    
    public UserInfoResponse extractUserDetails(Integer userId) {
        byte[] coverImgBytes = null;
        byte[] profileImgBytes = null;

        var coverImgPath = userRepository.findById(userId).get().getCoverImg();
        var profileImgPath = userRepository.findById(userId).get().getAvatar();

        if (coverImgPath != null) {
            try {
                coverImgBytes = firebaseStorageService.getFileFromFirebase(coverImgPath);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao buscar imagem de capa: " + e);
            }
        }
        if (profileImgPath != null) {
            try {
                profileImgBytes = firebaseStorageService.getFileFromFirebase(profileImgPath);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erro ao buscar imagem de avatar: " + e);
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