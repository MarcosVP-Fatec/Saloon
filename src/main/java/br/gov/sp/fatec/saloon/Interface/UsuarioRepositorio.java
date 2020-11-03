package br.gov.sp.fatec.saloon.Interface;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;

public interface UsuarioRepositorio {
    public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    }
}