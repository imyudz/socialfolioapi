package br.com.socialfolio.socialfolioapi.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
     
    private String socialName;
    private Date dtNasc;
    private String phone;
    private String avatar;
    private String description;
    private String state;
    private String city;
    private String coverImg;
    private Boolean employee;
    private String workplace;
    private String job_Level;
    private String recent_Education;
    private String  current_Company;

}
