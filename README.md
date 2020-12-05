# Saloon

## 4º Banco de Dados - Fatec SJC - 2020/2

## Professor:  

**Emanuel Mineda Carneiro** - *Laboratório IV de Desenvolvimento*   

**Giuliano Araujo Bertoti** - *Padrões de Projetos*  

---------------------------------------------------------------
## Integrantes
* **Marcos Vinicio Pereira**
* **Raian Silva Damaceno**

---------------------------------------------------------------
Nome do Projeto : **Saloon**     
![Saloon](https://github.com/MarcosVP-Fatec/Saloon/blob/master/src/main/resources/images/saloon_porta_64k.png?raw=true)  

## Objetivo do Projeto  

**Aluguel de salões e similares**

O sistema servirá para usuários proprietários de salões de festa ou espaços comerciais que são alugados por períodos muito curtos de uso (diário) que necessitam de controle de quais salões estão disponíveis conforme as necessidades dos clientes.  

## Objetos de Estudo aplicados neste projeto

  * Padrão de Projeto MVC (Model / View / Controler)  
    * MODEL  
      * JPA/Hibernate  
        - @MappedSuperclass (Campos de auditoria)  
        - @MappedSuperclass (Id)  
        - @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
      * TOOL  
        Ferramentas para manipulação de dados.
       
  * Padrão de Projeto Singleton  
    * PersistenceManager  
    * UsuarioLogado  

  * Padrão de Projetos DAO (Data Access Object)
    Todas as entidades

  * Padrão de Projetos Generics
    * Classe GeneratorAudit


> "É preciso amar as pessoas  
> Como se não houvesse amanhã  
> Por que se você parar pra pensar  
> Na verdade não há"  (Renato Russo)

# Modelo Conceitual do Projeto
![Modelo Conceitual do Projeto Saloon](https://github.com/MarcosVP-Fatec/Saloon/blob/master/SaloonConceitual.png?raw=true)

