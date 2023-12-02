package br.com.socialfolio.socialfolioapi.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String socialName;
    private String email;
    private String password;

    private String dtNasc;

    private String phone;
    private MultipartFile avatar;
    private String description;
    private String state;
    private String city;
    private MultipartFile coverImg;
    private Boolean employee;
    private String workplace;
    private String recent_Education;
    private String  current_Company;

}
