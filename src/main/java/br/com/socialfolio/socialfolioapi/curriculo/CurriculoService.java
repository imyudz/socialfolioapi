package br.com.socialfolio.socialfolioapi.curriculo;


import java.util.Optional;
import br.com.socialfolio.socialfolioapi.user.User;
import org.springframework.stereotype.Service;

import br.com.socialfolio.socialfolioapi.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculoService {
    
    private final CurriculoRepository curriculoRepository;
    private final UserRepository userRepository;
    
    public CurriculoResponse extractCurriculoDetails(Integer userId) {

        Optional<User> user = userRepository.findById(userId);
    
        if (user.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }

        var curriculo = curriculoRepository.findByFkUser(user.get())
            .orElseThrow();

        if (curriculo == null || curriculo.getAbout() == null || curriculo.getAbout().isEmpty()) {
            throw new RuntimeException("Curriculo information is empty or null");
        } else if (curriculo.getContact() == null || curriculo.getContact().isEmpty()) {
            throw new RuntimeException("Contact information is empty or null");
        } else if (curriculo.getFormation() == null || curriculo.getFormation().isEmpty()) {
            throw new RuntimeException("Formation information is empty or null");
        } else if (curriculo.getExperience() == null || curriculo.getExperience().isEmpty()) {
            throw new RuntimeException("Experience information is empty or null");
        } else if (curriculo.getSkill() == null || curriculo.getSkill().isEmpty()) {
            throw new RuntimeException("Skill information is empty or null");
        } else if (curriculo.getKnowledge() == null || curriculo.getKnowledge().isEmpty()) {
            throw new RuntimeException("Knowledge information is empty or null");
        } else if (curriculo.getLanguage() == null || curriculo.getLanguage().isEmpty()) {
            throw new RuntimeException("Language information is empty or null");
        }
        
        return CurriculoResponse.builder()
            .userId(userId)
            .about(curriculo.getAbout())
            .contact(curriculo.getContact())
            .formation(curriculo.getFormation())
            .experience(curriculo.getExperience())
            .skill(curriculo.getSkill())
            .knowledge(curriculo.getKnowledge())
            .language(curriculo.getLanguage())
            .build();
        
    }
}