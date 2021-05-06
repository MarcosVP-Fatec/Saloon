package br.gov.sp.fatec.saloon.service.regi;

import java.util.Date;

import org.aspectj.weaver.ResolvedMemberImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.exception.CpfInvalidoException;
import br.gov.sp.fatec.saloon.exception.EmailInvalidoException;
import br.gov.sp.fatec.saloon.exception.RegistroJaExisteException;
import br.gov.sp.fatec.saloon.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.saloon.model.entity.regi.Proprietario;
import br.gov.sp.fatec.saloon.model.repository.regi.ProprietarioRepository;
import br.gov.sp.fatec.saloon.model.repository.stat.UsuarioNivelRepository;
import br.gov.sp.fatec.saloon.model.tool.Validador;

@Service("proprietarioService")
@Configurable
public class ProprietarioServiceImpl implements ProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepo;

    @Autowired
    private UsuarioNivelRepository nivelRepo;

	private void altIncValidade( Proprietario proprietario
			                    , String apelido
    				            , String email
					            , String senha
					            , String nome
					            , Date   dtNascimento
					            , String cpf) {
		
		if (apelido.isEmpty()) {
			throw new RuntimeException("Apelido de usuário em branco (Obrigatório).");
		}
		
		if (email.isEmpty()) {
			throw new RuntimeException("E-mail de usuário em branco (Obrigatório).");
		}
		if (proprietario == null || !email.equals(proprietario.getEmail())) {
			if (!Validador.isEmail(email)){
                throw new EmailInvalidoException(email);
            };
		}
		
		if (proprietario == null || !cpf.equals(proprietario.getCpf())) {
			if (!Validador.cpf(cpf)){
                throw new CpfInvalidoException(cpf);
            }
		}
		
		if (nome.isEmpty()) {
			throw new RuntimeException("Nome de proprietário em branco (Obrigatório).");
		}
	}
	
    @Override
    public Proprietario inc(Proprietario proprietario){
        return inc( proprietario.getApelido()
                  , proprietario.getEmail()
                  , proprietario.getSenha()
                  , proprietario.getNome()
                  , proprietario.getDtNascimento()
                  , proprietario.getCpf());
    }

    @Override
    public Proprietario inc( String apelido
				            , String email
				            , String senha
				            , String nome
				            , Date   dtNascimento
				            , String cpf) {
		
		if (proprietarioRepo.existsByApelido(apelido)) {
			throw new RegistroJaExisteException("Apelido de usuário já existe: \"" + apelido + "\"");
		}
		
		if (proprietarioRepo.existsByCpf(cpf)) {
			throw new RegistroJaExisteException("CPF de usuário já existe: \"" + cpf + "\" >>>>>>> " + String.valueOf(cpf.length()));
		}

		altIncValidade(null, apelido, email, senha, nome, dtNascimento, cpf);
		return persist(apelido, email, senha, nome, dtNascimento, cpf);

	}

	@Override
    public Proprietario alt( String apelido
				            , String email
				            , String senha
				            , String nome
				            , Date   dtNascimento
				            , String cpf) {
		
		if (!proprietarioRepo.existsByApelido(apelido)) {
			throw new RegistroNaoEncontradoException("Apelido de usuário nao existe: \"" + apelido + "\"");
		}
		
		Proprietario proprietario = proprietarioRepo.findByApelido(apelido);
		
		if (!cpf.equals(proprietario.getCpf()) && !proprietarioRepo.existsByCpf(cpf)) {
			throw new RegistroNaoEncontradoException("Novo CPF de usuário já existe: \"" + apelido + "\"");
		}
		
		altIncValidade(proprietario, apelido, email, senha, nome, dtNascimento, cpf);
		return persist(apelido, email, senha, nome, dtNascimento, cpf);
		
	}
	
    @Override
    @Transactional
    public Proprietario persist( Long   id
                               , String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf) {
    
        Proprietario proprietario;

        if (id != null) {
            if (!proprietarioRepo.existsById(id) ){
                return null;
            }
            proprietario = proprietarioRepo.buscarPorId(id);
        } else {
            proprietario = new Proprietario();
        }

        proprietario.setApelido(apelido);
        proprietario.setEmail(email);
        proprietario.setSenha(senha);
        proprietario.setNome(nome);
        proprietario.setDtNascimento(dtNascimento);
        proprietario.setCpf(cpf);
        proprietario.setUsuarioNivel(nivelRepo.buscarPorId(2L));

        return proprietarioRepo.save(proprietario);
    }

    @Override
    public Proprietario persist( String apelido
                               , String email
                               , String senha
                               , String nome
                               , Date   dtNascimento
                               , String cpf){

        return this.persist( null
                           , apelido
                           , email
                           , senha
                           , nome
                           , dtNascimento
                           , cpf);
    
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!proprietarioRepo.existsById(id)){
            return true;
        }
        proprietarioRepo.deleteById(id);
        return !proprietarioRepo.existsById(id);
    }

    @Override
    public boolean delete(String apelido) {
        if (!proprietarioRepo.existsByApelido(apelido))
            return true;
        return this.delete(proprietarioRepo.findByApelido(apelido).getId());
    }

    public Proprietario buscaPorApelido(String apelidoUsuario){
                Proprietario proprietario = proprietarioRepo.findByApelido(apelidoUsuario);
        if (proprietario == null || proprietario.getId() == null){
            throw new RegistroNaoEncontradoException("Proprietário não encontrado (Apelido): \"" + apelidoUsuario + "\"");
        }    
        return proprietario;
    }


}
