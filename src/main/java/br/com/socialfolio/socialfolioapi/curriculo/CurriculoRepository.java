package br.com.socialfolio.socialfolioapi.curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.socialfolio.socialfolioapi.user.User;
import java.util.Optional;



public interface CurriculoRepository extends JpaRepository<Curriculo, Integer> {
    Optional<Curriculo> findByFkUser(User user);
}




