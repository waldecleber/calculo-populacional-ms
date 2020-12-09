CREATE TABLE IF NOT EXISTS cidade (
        id bigint not null auto_increment,       
        nome varchar(255),
        populacao int,
        id_estado  bigint,
        primary key (id),
        FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id`)
    ) engine=InnoDB
