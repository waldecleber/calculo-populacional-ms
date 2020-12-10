# Cálculo Populacional

### calculo-populacional-ms

Microsserviço para calcular o custo populacional de um estado brasileito,
baseado na quantidade populacional de suas cidades.

# Features!

- Calcula a quantidade populacional 
- Cada cidadão custa ao estado US$ 123.45 (valor em dólar);
- Há uma correção de cálculo adicional onde a população acima de 50000
  recebe um ajuste de -12.3% no valor
- Busca a cotação do dólar atual, através da API:  
  - https://economia.awesomeapi.com.br/USD-BRL


## Tecnologias


* Java 11
* Spring Boot
* Maven
* Liquibase
* MySql
* Swagger
* Feign Client
* Mockito TDD


## Instalação

calculo-populacional-ms requer Maven para rodar

```sh
$ mvn -v
Apache Maven 3.6.0
```

Para rodar o serviço em localhost execute o comando a seguir:

```sh
$ cd calculo-populacional-ms
$ mvn clean install
```

environments...

dev = para rodar o container Docker MySql 
```sh
$ mvn clean install -Pdev
```

prod = para rodar com as configurações do Heroku
```sh
$ mvn clean install -Pprod
```


## Docker
calculo-populacional-ms é fácil de deployar e instalar como container Docker.

Por default, o Docker irá expor a porta 7001, então a porta pode ser modificada caso haja necessidade.

```sh
$ cd calculo-populacional-ms
$ docker build -t waldecleber/calculo-populacional-ms:latest .
```

```sh
$ docker run -p 7001:8000 --network="mysql_backend" -e "spring.profiles.active=localhost" waldecleber/calculo-populacional-ms
```


Verifique o deployment sendo executado, navegando até o endereço no seu browser preferido.

```sh
http://localhost:8000/swagger-ui.html
```
