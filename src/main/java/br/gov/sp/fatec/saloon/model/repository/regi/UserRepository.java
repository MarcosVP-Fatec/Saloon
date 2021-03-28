package br.gov.sp.fatec.saloon.model.repository.regi;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{
    
    public Usuario findByApelido(String apelido);

}
