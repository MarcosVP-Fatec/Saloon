package br.gov.sp.fatec.saloon.model.service.regi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.saloon.model.entity.regi.Cliente;
import br.gov.sp.fatec.saloon.model.repository.regi.ClienteRepository;
import br.gov.sp.fatec.saloon.model.repository.regi.ParceiroRepository;

@Service("ClienteService")
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository   clienteRepo;

    @Autowired
    private ParceiroRepository  parceiroRepo;

    @Override
    @Transactional
    public Cliente persist( Long   id
                          , String cpf_cnpj
                          , String nome
                          , String telDdd
                          , String telNumero
                          , Long   idParceiro) {

        
        Cliente cliente;
        
        if (id != null) {
            if (!clienteRepo.existsById(id) ){
                return null;
            }
            cliente = clienteRepo.buscarPorId(id);
        } else {
            cliente = new Cliente();
        }

        cliente.setCpf_cnpj(cpf_cnpj);
        cliente.setNome(nome);
        cliente.setTelDdd(telDdd);
        cliente.setTelNumero(telNumero);

        if (idParceiro != null){
            cliente.setParceiro(parceiroRepo.buscarPorId(idParceiro));
        }

        return clienteRepo.save(cliente);
    }

    @Override
    public Cliente persist( String cpf_cnpj
                          , String nome
                          , String telDdd
                          , String telNumero
                          , Long idParceiro) {
        return this.persist(null, cpf_cnpj, nome, telDdd, telNumero, idParceiro);
    }

    @Override
    public Cliente persist( String cpf_cnpj
                          , String nome
                          , String telDdd
                          , String telNumero) {
        return this.persist(null, cpf_cnpj, nome, telDdd, telNumero, null);
    }

    @Override
    public Cliente persist( String cpf_cnpj
                          , String nome) {
        return this.persist(null, cpf_cnpj, nome, null, null, null);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!clienteRepo.existsById(id)) return true;
        clienteRepo.deleteById(id);
        return !clienteRepo.existsById(id);
    }

    @Override
    public boolean delete(String nome) {
        if (!clienteRepo.existsByNome(nome)) return true;
        return this.delete(clienteRepo.findByNome(nome).getId());
    }
    
}
