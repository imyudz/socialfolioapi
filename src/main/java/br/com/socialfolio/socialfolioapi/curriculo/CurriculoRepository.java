package br.com.socialfolio.socialfolioapi.curriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface CurriculoRepository extends JpaRepository<Curriculo, Integer> {
    Optional<Curriculo> findByAbout (String about);
    
    List<Curriculo> findByFormation(String formation);

    List<Curriculo> findByExperience(String experience);

    Optional<Curriculo> findByContact(String contact);

    List<Curriculo> findBySkill(String skill);

    List<Curriculo> findByKnowledge(String knowledge);

    List<Curriculo> findByLanguage(String language);

    Optional<Curriculo> findByAvatar(String avatar);
}




