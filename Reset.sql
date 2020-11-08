-- Exclui tudo para recriar
drop schema if exists saloon;
drop user if exists 'saloonsys'@'localhost';

source DDL.sql
