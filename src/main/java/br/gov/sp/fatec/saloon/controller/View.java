package br.gov.sp.fatec.saloon.controller;

public class View {
    
    public static class AuditInc extends AuditAlt{}

    public static class AuditAlt{}

    public static class UsuariosResumido{}

    public static class UsuarioProprietario extends AuditInc{}

    public static class ProprietarioApelidoUsuario extends AuditInc{}

}
