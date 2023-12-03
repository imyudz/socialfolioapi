package br.com.socialfolio.socialfolioapi.curriculo;

//NotaÃ§Ãµes Lombok para reduzir cÃ³digo boilerplate (repetitivo)

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//Lombok Annotations (remove boilerplate)
@Data //Faz o mesmo que gerar getters e setters
@Builder //Gera um construtor melhor para a classe, seguindo design patters
@NoArgsConstructor
@AllArgsConstructor

//Hibernate Annotations (configuraÃ§Ãµes de ORM - Banco de Dados)
@Entity //Ao utilizar o @Entity, vocÃª estÃ¡ dizendo ao Hibernate para mapear aquela classe para uma tabela no banco de dados.
@Table(name="_curriculo") //nome da tabela no bd

//Aqui, pode ser implementada a interface UserDetails diretamente do spring security
//Ou pode ser extendida a classe User do spring security

public class Curriculo {

    @Id
    @GeneratedValue //gera um valor automÃ¡tico para esse id
    private Integer id;

  
    private String about;
    private String formation;
    private String experience;
    private String contact;
    private String skill;
    private String knowledge;
    private String language;
    private String avatar;

    // @Enumerated(EnumType.STRING) //Define que Ã© um enum e pega os valores de string deles
    // private Role role; // cargo
    
}
