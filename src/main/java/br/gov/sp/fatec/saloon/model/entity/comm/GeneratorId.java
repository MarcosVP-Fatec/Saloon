package br.gov.sp.fatec.saloon.model.entity.comm;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @apiNote GeneretorId() --> Classe que faz a geração automática do Id de todas as classes.
 * @version 1.1 (Spring-boot)
 */

@MappedSuperclass
public class GeneratorId extends GeneratorAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //CONSTRUTOR
    public GeneratorId(){
        super();
    }

    public Long getId()        { return id; }
    public void setId(Long id) { this.id = id; }

}

