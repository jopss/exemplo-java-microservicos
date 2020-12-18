# microservico-cpf

Componentizacao em microservico de validacao de cpf.

Tecnologias:

* SpringBoot

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main MicroserviceCPFApplication.

Ao subir pode-se acessar a verificacao de saude:

```
http://localhost:8086/actuator/health
```

Para rodar via docker:

```
docker build -t microservice-cpf .
docker run -p 8086:8086 microservice-cpf
```

## Documentação API

Esta disponivel os seguintes docs

```
JSON: http://localhost:8086/api-docs/
UI: http://localhost:8086/swagger.html
```

## Testes

Foi decido que os testes serão de integração e ocorrerão na camada Controller e Service (regras) ao mesmo tempo, mockando o necessário.

## Observações

Como exemplo nao esta indo na receita federal (base paga), somente validando modulo 11.
