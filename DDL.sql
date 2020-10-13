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
      pro_usu_id          bigint unsigned
    , pro_dt_inicio       datetime
    , pro_dt_limite       date
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
    , constraint pro_usu_id_fk foreign key (pro_usu_id)
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
    , alu_pro_id            bigint unsigned
    , alu_alt_id            bigint unsigned
    , alu_descr             varchar(50)
    , alu_endereco          varchar(500)
    , alu_capacidade        int
    , alu_valor             decimal(10,2)
    , _inc_usua             bigint          
    , _inc_data             datetime            
    , _alt_usua             bigint          
    , _alt_data             datetime            
);

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