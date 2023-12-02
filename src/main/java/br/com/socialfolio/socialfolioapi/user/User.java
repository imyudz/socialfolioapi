package br.com.socialfolio.socialfolioapi.user;

//NotaÃ§Ãµes Lombok para reduzir cÃ³digo boilerplate (repetitivo)

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

//Lombok Annotations (remove boilerplate)
@Data //Faz o mesmo que gerar getters e setters
@Builder //Gera um construtor melhor para a classe, seguindo design patters
@NoArgsConstructor
@AllArgsConstructor

//Hibernate Annotations (configuraÃ§Ãµes de ORM - Banco de Dados)
@Entity //Ao utilizar o @Entity, vocÃª estÃ¡ dizendo ao Hibernate para mapear aquela classe para uma tabela no banco de dados.
@Table(name="_user", uniqueConstraints= {@UniqueConstraint (columnNames= {"email"})}) //nome da tabela no bd
   
//Aqui, pode ser implementada a interface UserDetails diretamente do spring security
//Ou pode ser extendida a classe User do spring security

public class User implements UserDetails {

    @Id
    @GeneratedValue //gera um valor automÃ¡tico para esse id
    private Integer id;
    
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
    private String recent_Education;
    private String  current_Company;

    @Enumerated(EnumType.STRING) //Define que Ã© um enum e pega os valores de string deles
    private Role role; // cargo

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
