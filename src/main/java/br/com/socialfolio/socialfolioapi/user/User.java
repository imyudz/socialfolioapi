package br.com.socialfolio.socialfolioapi.user;

//Notações Lombok para reduzir código boilerplate (repetitivo)

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//Lombok Annotations (remove boilerplate)
@Data //Faz o mesmo que gerar getters e setters
@Builder //Gera um construtor melhor para a classe, seguindo design patters
@NoArgsConstructor
@AllArgsConstructor

//Hibernate Annotations (configurações de ORM - Banco de Dados)
@Entity //Ao utilizar o @Entity, você está dizendo ao Hibernate para mapear aquela classe para uma tabela no banco de dados.
@Table(name="_user") //nome da tabela no bd

//Aqui, pode ser implementada a interface UserDetails diretamente do spring security
//Ou pode ser extendida a classe User do spring security

public class User implements UserDetails {

    @Id
    @GeneratedValue //gera um valor automático para esse id
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING) //Define que é um enum e pega os valores de string deles
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
