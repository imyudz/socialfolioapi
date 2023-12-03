package br.com.socialfolio.socialfolioapi.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Integer userId;
    private String name;
    private String socialName;
    private String email;
    private String dtNasc;
    private String phone;
    private byte[] avatar;
    private String description;
    private String state;
    private String city;
    private byte[] coverImg;
    private Boolean employee;
    private String workplace;
    private String recent_Education;
    private String  current_Company;
    private String profission;
}
