CREATE TABLE IF NOT EXISTS estado (
        id bigint not null auto_increment,
        uf varchar(2),
        nome varchar(255),
        bandeira varchar(255),
        primary key (id)
    ) engine=InnoDB;
    
INSERT INTO `estado` (`uf`, `nome`, `bandeira`) VALUES ('RS', 'Rio Grande do Sul', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/63/Bandeira_do_Rio_Grande_do_Sul.svg/200px-Bandeira_do_Rio_Grande_do_Sul.svg.png');
INSERT INTO `estado` (`uf`, `nome`, `bandeira`) VALUES ('SC', 'Santa Catarina', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Bandeira_de_Santa_Catarina.svg/200px-Bandeira_de_Santa_Catarina.svg.png');    
INSERT INTO `estado` (`uf`, `nome`, `bandeira`) VALUES ('PR', 'Paraná', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Bandeira_do_Paran%C3%A1.svg/200px-Bandeira_do_Paran%C3%A1.svg.png');

