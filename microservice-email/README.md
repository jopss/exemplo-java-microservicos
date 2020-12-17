# microservico-email

Componentizacao em microservico de envio de email externo.

Tecnologias:

* SpringBoot
* JavaMail

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main MicroserviceEmailApplication.

Ao subir pode-se acessar a verificacao de saude:

```
http://localhost:8087/actuator/health
```

Para rodar via docker:

```
docker build -t microservice-email .
docker run -p 8087:8087 microservice-email
```

## Documentação API

Esta disponivel os seguintes docs

```
JSON: http://localhost:8087/api-docs/
UI: http://localhost:8087/swagger.html
```

## Observações

O cliente SMTP real de email escolhido é gratutito e eventualmente pode estar fora do ar.
