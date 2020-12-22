# microservico-documento

Componentizacao em microservico de tratamento sobre a imagem do documento de uma pessoa. O arquivo fisico é armazenado em disco.

Tecnologias:

* SpringBoot
* JPA e SpringData

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main MicroserviceDocumentoApplication.

Ao subir pode-se acessar a verificacao de saude:

```
http://localhost:8088/actuator/health
```

Para rodar via docker:

```
docker build -t microservice-documento .
docker run -p 8088:8088 microservice-documento
```

## Documentação API

Esta disponivel os seguintes docs

```
JSON: http://localhost:8088/api-docs/
UI: http://localhost:8088/swagger.html
```

## Banco de Dados

Foi utilizado banco em memoria h2. Para acessar o console:

```
http://localhost:8088/h2-console
```

```
Usuario: sa
Senha: (vazio)
Banco: jdbc:h2:mem:documentodb
```

## Testes

Foi decido que os testes serão de integração e ocorrerão na camada Controller e Service (regras) ao mesmo tempo, mockando o necessário.

## Observações

Como o banco de dados deste exemplo esta em memoria, ao matar o processo docker ou finalizar o tomcat,
irá limpar a base de dados e os arquivos fisicos. Para sistemas reais, configurar um banco externo como postgresql e filesystem externo ao docker.
