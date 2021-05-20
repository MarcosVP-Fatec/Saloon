-- ------------------------------------------------------------------------
-- cria schema e usuário
-- ------------------------------------------------------------------------
create schema if not exists saloon;
use saloon;
create user if not exists 'saloonsys'@'localhost' identified by 'M@triz';
grant select, insert, delete, update on saloon.* to saloonsys@'localhost';

-- ------------------------------------------------------------------------
-- MES_ANO
-- ------------------------------------------------------------------------
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

-- ------------------------------------------------------------------------
-- NÍVEL DE USUARIO
-- 1-Administrador
-- 2-Proprietário
-- 3-Parceiro
-- 4-Cliente
-- ------------------------------------------------------------------------
create table niv_usuario(
      niv_id                bigint unsigned primary key
    , niv_key               varchar(20)     not null
    , niv_descr             varchar(20)     not null
    , niv_adm               boolean         not null
    , niv_prop              boolean         not null
    , niv_parc              boolean         not null
    , niv_cli               boolean         not null
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint niv_key_uk   unique (niv_key)
    , constraint niv_descr_uk unique (niv_descr)
);

-- ------------------------------------------------------------------------
-- USUARIO
-- ------------------------------------------------------------------------
create table usu_usuario (
      usu_id                bigint unsigned primary key auto_increment
    , usu_apelido           varchar(30)     not null
    , usu_email             varchar(255)    not null
    , usu_senha             varchar(100)    not null
    , usu_senha_validade    date
    , usu_pj_ou_pf          varchar(1)     
    , usu_nome              varchar(80)     not null
    , usu_dt_nascimento     date            not null
    , usu_cpf_cnpj          varchar(14)     not null
    , usu_nivel             bigint unsigned not null
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , usu_cod_nova_senha    bigint  
    , constraint usu_apelido_uk unique (usu_apelido)
    , constraint usu_email_uk unique (usu_email)
    , constraint usu_nivel_pk foreign key (usu_nivel)
        references niv_usuario (niv_id)
);

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
    , constraint alu_descr_uk unique (alu_descr)
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
    , constraint cli_nome_uk unique (cli_nome)
);

create table ctm_contrato_motivo (
      ctm_id                bigint unsigned primary key auto_increment
    , ctm_descr             varchar(30)
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint ctm_descr_uk unique (ctm_descr)
);

create table ctt_contrato (
      ctt_id                bigint unsigned primary key auto_increment
    , ctt_cli_id            bigint unsigned not null
    , ctt_alu_id            bigint unsigned not null
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
    , constraint ctt_alu_id_fk foreign key (ctt_alu_id)    
        references alu_alugavel (alu_id)
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

create table par_parametro (
      par_id                bigint unsigned primary key auto_increment
    , par_cod               varchar(30)    not null
    , par_descr             varchar(100)
    , par_tipo              char(1)        not null -- int, String, Date
    , par_val_num           double
    , par_val_str           varchar(4000)
    , par_val_dat           Date
    , par_val_bol           boolean
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint par_cod_uk unique (par_cod) 
);
