package br.gov.sp.fatec.saloon.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @apiNote GeneretorId() --> Classe que faz a geração automática do Id de todas as classes.
 */

@MappedSuperclass
public class GeneratorId extends GeneratorAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId()        { return id; }
    public void setId(Long id) { this.id = id; }

}

