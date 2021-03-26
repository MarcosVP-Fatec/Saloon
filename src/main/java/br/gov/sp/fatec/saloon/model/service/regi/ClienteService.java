package br.gov.sp.fatec.saloon.model.service.regi;

public interface ClienteService {

    public Cliente persist( Long     id
                          , String   cpf_cnpj
                          , String   nome
                          , String   telDdd
                          , String   telNumero
                          , Long     idParceiro);


     cli_id                bigint unsigned primary key auto_increment
    , cli_cpf_cnpj          varchar(14) not null
    , cli_nome              varchar(80) not null
    , cli_tel_ddd           varchar(2)   
    , cli_tel_numero        varchar(10) 
    , cli_pco_id            bigint unsigned

    @Column(name = "cli_cpf_cnpj")          private String cpf_cnpj;        //14
    @Column(name = "cli_nome")              private String nome;            //80
    @Column(name = "cli_tel_ddd")           private String telDdd;          //2
    @Column(name = "cli_tel_numero")        private String telNumero;       //10

    //A chave estrangeira não se mapeia diretamente
    //x to y => x é da entidade atual.
    //No JoinColumn definir o nome da coluna fk desta entidade.
    @OneToOne(fetch = FetchType.LAZY)    
    @JoinColumn(name = "cli_pco_id")        private Parceiro parceiro;

    public Cliente( String   cpf_cnpj
                  , String   nome
                  , String   telDdd
                  , String   telNumero
                  , Parceiro parceiro){
        this(cpf_cnpj,nome,telDdd,telNumero);
        setParceiro(parceiro);
    }
    public Cliente( String   cpf_cnpj
                  , String   nome
                  , String   telDdd
                  , String   telNumero){
        this(cpf_cnpj,nome);
        setTelDdd(telDdd);
        setTelNumero(telNumero);
    }
    public Cliente( String   cpf_cnpj
                  , String   nome){
        setCpf_cnpj(cpf_cnpj);
        setNome(nome);
    }



    
}


    /**
     * @apiNote persist(...) --> Inclui/Altera um usuário passando os seus campos
     * @param id (Long)
     * @param apelido (String)
     * @param email (String)
     * @param senha (String)
     * @param nome (String)
     * @param dtNascimento (Date)
     * @param cpf (String)
     * @param usuarioNivel Long
     * @return Usuario
     */

    public Usuario persist( String apelido
                          , String email
                          , String senha
                          , String nome
                          , Date   dtNascimento
                          , String cpf
                          , Long   usuarioNivel);
    
    /**
     * @apiNote delete(Long) --> Exclui um usuário passando o id
     * @param id (Long)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(Long id);

    /**
     * @apiNote delete(String) --> Exclui um usuário passando o apelido
     * @param apelido (String)
     * @return boolean / true se excluiu ou se não existe.
     */
    public boolean delete(String apelido);
    

