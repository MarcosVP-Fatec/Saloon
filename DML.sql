-- cria schema e usuário

-- ------------------------------------------------------------------------
-- MES_ANO
-- ------------------------------------------------------------------------
insert into mes_ano (mes_numero, mes_descr) values ('01','Janeiro');
insert into mes_ano (mes_numero, mes_descr) values ('02','Fevereiro');
insert into mes_ano (mes_numero, mes_descr) values ('03','Março');
insert into mes_ano (mes_numero, mes_descr) values ('04','Abril');
insert into mes_ano (mes_numero, mes_descr) values ('05','Maio');
insert into mes_ano (mes_numero, mes_descr) values ('06','Junho');
insert into mes_ano (mes_numero, mes_descr) values ('07','Julho');
insert into mes_ano (mes_numero, mes_descr) values ('08','Agosto');
insert into mes_ano (mes_numero, mes_descr) values ('09','Setembro');
insert into mes_ano (mes_numero, mes_descr) values ('10','Outubro');
insert into mes_ano (mes_numero, mes_descr) values ('11','Novembro');
insert into mes_ano (mes_numero, mes_descr) values ('12','Dezembro');
commit;

-- ------------------------------------------------------------------------
-- NÍVEL DE USUARIO
-- 1-Administrador
-- 2-Proprietário
-- 3-Parceiro
-- 4-Cliente
-- ------------------------------------------------------------------------
insert into niv_usuario (niv_id,niv_key,niv_descr,niv_adm,niv_prop,niv_parc,niv_cli) values (1,'ROLE_ADMIN'        ,'Administrador'   ,1,0,0,0);
insert into niv_usuario (niv_id,niv_key,niv_descr,niv_adm,niv_prop,niv_parc,niv_cli) values (2,'ROLE_PROPRIETARIO' ,'Proprietário'    ,0,1,0,0);
insert into niv_usuario (niv_id,niv_key,niv_descr,niv_adm,niv_prop,niv_parc,niv_cli) values (3,'ROLE_PARCEIRO'     ,'Parceiro'        ,0,0,1,0);
insert into niv_usuario (niv_id,niv_key,niv_descr,niv_adm,niv_prop,niv_parc,niv_cli) values (4,'ROLE_CLIENTE'      ,'Cliente'         ,0,0,0,1);
insert into niv_usuario (niv_id,niv_key,niv_descr,niv_adm,niv_prop,niv_parc,niv_cli) values (5,'ROLE_PARCLI'       ,'Parceiro/Cliente',0,0,1,1);
commit;

-- ------------------------------------------------------------------------
-- Cadastra o usuário administrador inicial necessário para usar o sistema
-- ------------------------------------------------------------------------
insert into usu_usuario ( usu_apelido
                         , usu_email                   
                         , usu_senha
                         , usu_pj_ou_pf
                         , usu_nome       
                         , usu_dt_nascimento
                         , usu_cpf_cnpj
                         , usu_nivel) 
                 values ( "ADMIN"      
                         , "administrador@saloon.com.br"
                         , "$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C"   
                         , "J"          
                         , "Administrador"
                         , '1969-04-12'     
                         , '11111111111111'
                         , 1 );

update usu_usuario set _inc_usua = 1, _inc_data = now() where usu_id = 1;

commit;

-- ------------------------------------------------------------------------
-- ALUGAVEL_TIPO - Tipo do local que será alugado
-- ------------------------------------------------------------------------
-- 1-Salão de Festas
-- 2-Salão de Palestras
-- 3-Salão de Reuniões
-- 4-Sala de Aula
-- 5-Terreno Vazio
-- ------------------------------------------------------------------------
insert alt_alugavel_tipo (alt_descr) values ('Salão de Festas');
insert alt_alugavel_tipo (alt_descr) values ('Salão de Palestras');
insert alt_alugavel_tipo (alt_descr) values ('Salão de Reuniões');
insert alt_alugavel_tipo (alt_descr) values ('Sala de Aula');
insert alt_alugavel_tipo (alt_descr) values ('Terreno Vazio');

-- ------------------------------------------------------------------------
-- CTM_CONTRATO_MOTIVO
-- ------------------------------------------------------------------------
insert into ctm_contrato_motivo (ctm_descr) values ("Aniversário"           );
insert into ctm_contrato_motivo (ctm_descr) values ("Bodas"                 );
insert into ctm_contrato_motivo (ctm_descr) values ("Casamento"             );
insert into ctm_contrato_motivo (ctm_descr) values ("Confraternização"      );
insert into ctm_contrato_motivo (ctm_descr) values ("Encontro de Amigos(as)");
insert into ctm_contrato_motivo (ctm_descr) values ("Outros"                );

