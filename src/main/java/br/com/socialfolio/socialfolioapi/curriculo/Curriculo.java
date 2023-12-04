package br.com.socialfolio.socialfolioapi.curriculo;

import br.com.socialfolio.socialfolioapi.user.User;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera um valor automÃ¡tico para esse id
    private Integer id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String about;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String formation;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String experience;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String contact;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String skill;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String knowledge;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String language;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fkUser", unique = true)
    private User fkUser;
    
}
