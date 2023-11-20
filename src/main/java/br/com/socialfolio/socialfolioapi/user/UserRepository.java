package br.com.socialfolio.socialfolioapi.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Comunica com o banco de dados
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
