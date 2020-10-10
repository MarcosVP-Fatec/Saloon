-- Exclui tudo para recriar
drop schema if exists saloon;
drop user if exists 'saloonsys'@'localhost';

-- cria schema e usuário
create schema saloon;
use saloon;
create user 'saloonsys'@'localhost' identified by 'M@triz';
grant select, insert, delete, update on saloon.* to saloonsys@'localhost';

-- USUARIO
create table usu_usuario (
    usu_id              bigint unsigned primary key auto_increment,
    usu_nome_usuario    varchar(80)     not null,
    usu_senha           varchar(50)     not null,
    _inc_usua           varchar(80),
    _inc_data           datetime,
    constraint usu_nome_usuario_uk unique (usu_nome_usuario)
);

/*
-- ALUNO
create table alu_aluno (
    alu_id              bigint unsigned primary key,
    alu_ra              bigint unsigned not null,
    _inc_usua           varchar(80),
    _inc_data           datetime,
    constraint alu_usu_kf foreign key (alu_id)
        references usu_usuario (usu_id),
    constraint alu_ra_uk unique (alu_ra)
);

-- PROFESSOR
create table pro_professor (
    pro_id              bigint unsigned primary key,
    pro_titulo          varchar(10),
    _inc_usua           varchar(80),
    _inc_data           datetime,
    constraint pro_usu_fk foreign key (pro_id)
        references usu_usuario (usu_id)
);

-- TRABALHO
create table tra_trabalho (
    tra_id                  bigint unsigned primary key auto_increment,
    tra_titulo              varchar(50)     not null,
    tra_data_hora_entrega   datetime,
    tra_local_arquivo       varchar(200)    not null,
    pro_avaliador_id        bigint unsigned,
    _inc_usua           varchar(80),
    _inc_data           datetime,
    constraint tra_pro_fk   foreign key (pro_avaliador_id)
    references pro_professor (pro_id)
);

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