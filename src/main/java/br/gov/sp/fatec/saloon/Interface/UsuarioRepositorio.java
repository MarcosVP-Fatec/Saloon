package br.gov.sp.fatec.saloon.Interface;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;


import br.gov.sp.fatec.saloon.model.entity.regi.Usuario;
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

}
