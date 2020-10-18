-- Exclui tudo para recriar
drop schema if exists saloon;
drop user if exists 'saloonsys'@'localhost';

-- cria schema e usuário
create schema saloon;
use saloon;
create user 'saloonsys'@'localhost' identified by 'M@triz';
grant select, insert, delete, update on saloon.* to saloonsys@'localhost';

-- ------------------------------------------------------------------------
-- USUARIO
-- ------------------------------------------------------------------------
create table usu_usuario (
      usu_id                bigint unsigned primary key auto_increment
    , usu_apelido           varchar(30)     not null
    , usu_email             varchar(255)    not null
    , usu_senha             varchar(20)     not null
    , usu_senha_validade    date
    , usu_pj_ou_pf          varchar(1)     
    , usu_nome              varchar(80)     not null
    , usu_dt_nascimento     date            not null
    , usu_cpf_cnpj          varchar(14)     not null
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , usu_cod_nova_senha    bigint  
    , constraint usu_apelido_uk unique (usu_apelido)
    , constraint usu_email_uk unique (usu_email)
);

-- ------------------------------------------------------------------------
-- Cadastra o usuário administrador inicial necessário para usar o sistema
-- ------------------------------------------------------------------------
insert into usu_usuario (usu_apelido, usu_email                   , usu_senha, usu_pj_ou_pf,usu_nome       ,usu_dt_nascimento,usu_cpf_cnpj) 
                 values ("ADM"      ,"administrator@saloon.com.br","pwADM"   ,"J"          ,"Administrador",'1969-04-01'     ,'11111111111111' );

update usu_usuario set _inc_usua = 1, _inc_data = now() where usu_id = 1;

commit;

-- ------------------------------------------------------------------------
-- PROPRIETARIO 
-- ------------------------------------------------------------------------
create table pro_proprietario (
      pro_usu_id            bigint unsigned
    , pro_dt_inicio         datetime
    , pro_dt_limite         date
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint pro_usu_id_fk foreign key (pro_usu_id)
         references usu_usuario (usu_id)
);

-- ------------------------------------------------------------------------
-- PARCEIRO
-- ------------------------------------------------------------------------
create table par_parceiro (
      par_usu_id            bigint unsigned
    , par_dt_inicio         datetime
    , par_inativo           bit
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint par_usu_id_fk foreign key (par_usu_id)
         references usu_usuario (usu_id)
);

-- ------------------------------------------------------------------------
-- ALUGAVEL_TIPO - Tipo do local que será alugado
-- ------------------------------------------------------------------------
-- 1-Salão de Festas
-- 2-Salão de Palestras
-- 3-Salão de Reuniões
-- 4-Sala de Aula
-- 5-Terreno Vazio
-- ------------------------------------------------------------------------
create table alt_alugavel_tipo (
      alt_id                bigint unsigned primary key auto_increment
    , alt_descr             varchar(20)     not null
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
);

-- ------------------------------------------------------------------------
-- ALUGAVEL - Objeto da locação
-- ------------------------------------------------------------------------
create table alu_alugavel(
      alu_id                bigint unsigned primary key auto_increment
    , alu_pro_id            bigint unsigned not null
    , alu_alt_id            bigint unsigned not null
    , alu_descr             varchar(50)
    , alu_endereco          varchar(500)
    , alu_capacidade        int unsigned
    , alu_valor             decimal(10,2) unsigned
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint alu_pro_id_fk foreign key (alu_pro_id)
         references pro_proprietario (pro_usu_id)
    , constraint alu_alt_id_fk foreign key (alu_alt_id)
         references alt_alugavel_tipo (alt_id)
);

-- ------------------------------------------------------------------------
-- ALUGAVEL - Objeto da locação
-- ------------------------------------------------------------------------
create table cli_cliente (
      cli_id                bigint unsigned primary key auto_increment
    , cli_cpf_cnpj          varchar(14) not null
    , cli_nome              varchar(80) not null
    , cli_tel_ddd           varchar(2)   
    , cli_tel_numero        varchar(10) 
    , cli_pco_id            bigint unsigned
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint cli_cpf_cnpj_uk unique (cli_cpf_cnpj)
);

create table ctm_contrato_motivo (
      ctm_id                bigint unsigned primary key auto_increment
    , ctm_descr             varchar(30)
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
);

create table mes_ano (
      mes_id                bigint unsigned primary key auto_increment
    , mes_numero            varchar(2) not null
    , mes_descr             varchar(9) not null
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint mes_numero_uk unique (mes_numero)
    , constraint mes_descr_uk unique (mes_descr)
);

create table ctt_contrato (
      ctt_id                bigint unsigned primary key auto_increment
    , ctt_cli_id            bigint unsigned not null
    , ctt_data              date            not null
    , ctt_reserva_paga      decimal(10,2)   not null
    , ctt_ctm_id            bigint unsigned not null
    , ctt_festejo_nomes     varchar(4000)
    , ctt_festejo_mes_id    bigint unsigned 
    , ctt_festejo_dia       int unsigned
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint ctt_ctm_id_fk foreign key (ctt_ctm_id) 
        references ctm_contrato_motivo (ctm_id)
    , constraint ctt_festejo_mes_id_fk foreign key (ctt_festejo_mes_id)
        references mes_ano (mes_id)
);

create table pxc_parceiro_x_cliente (
      pxc_par_usu_id        bigint unsigned 
    , pxc_cli_id            bigint unsigned 
    , primary key pxc_parceiro_x_cliente_pk (pxc_par_usu_id, pxc_cli_id)
    , constraint pxc_par_id_fk foreign key (pxc_par_usu_id) 
        references par_parceiro (par_usu_id)
    , constraint pxc_cli_id_fk foreign key (pxc_cli_id) 
        references cli_cliente (cli_id)
);

create table alc_alugavel_contratado (
      alc_alu_id            bigint unsigned
    , alc_ctt_id            bigint unsigned
    , primary key alc_alugavel_contratado_pk (alc_alu_id, alc_ctt_id)
    , constraint alc_alu_id_fk foreign key (alc_alu_id)
        references alu_alugavel (alu_id) 
    , constraint alc_ctt_id_fk foreign key (alc_ctt_id)
        references ctt_contrato (ctt_id)    
)

/*

-- ENTREGA
-- Não tem atributos próprios
create table ent_entrega (
    alu_id bigint unsigned,
    tra_id bigint unsigned,
    _inc_usua           varchar(80),
    _inc_data           datetime,
    primary key (alu_id, tra_id),
    constraint ent_alu_fk foreign key (alu_id)
        references alu_aluno (alu_id),
    constraint ent_tra_fk foreign key (tra_id)
        references tra_trabalho (tra_id)
);
*/