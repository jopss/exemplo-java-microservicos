# microservico-email

Componentizacao em microservico de envio de email externo.

Tecnologias:

* SpringBoot
* JavaMail

## Executar a aplicação

Para rodar a aplicação na sua IDE, execute a classe main MicroserviceEmailApplication.

Para subir via docker:

```
docker build -t microservice-email .
docker run -p 8087:8087 microservice-email
```

## Observações

O cliente SMTP real de email escolhido é gratutito e eventualmente pode estar fora do ar.
