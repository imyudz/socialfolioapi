package br.com.socialfolio.socialfolioapi.curriculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurriculoResponse {
    private Integer userId;
    private Integer curriculoId;
    private String about;
    private String formation;
    private String experience;
    private String contact;
    private String skill;
    private String knowledge;
    private String language;
    private String errorMessage;
}
