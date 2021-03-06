# microservico-proposta

Componentizacao em microservico de tratamento sobre endereços. Ao passar um CEP, vai por integração externa ao ViaCep,
e no retorno, guarda na base local para economizar proximas chamadas.

Tecnologias:

* SpringBoot
* JPA e SpringData
* OpenFeign

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main MicroserviceEnderecoApplication.

Ao subir pode-se acessar a verificacao de saude:

```
http://localhost:8080/actuator/health
```

Para rodar via docker:

```
docker build -t microservice-proposta .
docker run -p 8080:8080 microservice-proposta
```

## Documentação API

Esta disponivel os seguintes docs

```
JSON: http://localhost:8080/api-docs/
UI: http://localhost:8080/swagger.html
```

## Banco de Dados

Foi utilizado banco em memoria h2. Para acessar o console:

```
http://localhost:8080/h2-console
```

```
Usuario: sa
Senha: (vazio)
Banco: jdbc:h2:mem:propostadb
```

## Testes

Foi decido que os testes serão de integração e ocorrerão na camada Controller e Service (regras) ao mesmo tempo, mockando o necessário.

## Observações

Como o banco de dados deste exemplo esta em memoria, ao matar o processo docker ou finalizar o tomcat,
irá limpar a base de dados. Para sistemas reais, configurar um banco externo como postgresql.
