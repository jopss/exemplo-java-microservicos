# Exemplo Microservico JAVA

Conjunto de projetos neste mono repo contendo um dominio especifico de exemplificacao de um cadastro, uma proposta de conta corrente online.

Foi levantado os seguintes microserviços abaixo, com suas portas de conexão web:

![alt text](https://github.com/jopss/exemplo-java-microservicos/blob/main/docs/micros.png?raw=true)

Tudo inicia-se com uma Proposta. Ela contem etapas, e cada uma valida, formata e salva os dados. Apos uma proposta pronta, envia-se um email para o cliente para que ele aceite a proposta. Ao aceitar, cria-se um usuario e senha, e uma conta é cadastrada para o cliente. Com a conta pronta, pode-se transferir valores entre elas.

Imagine telas wizard com o seguinte fluxo:

* Etapa 1 - Criar dados do cliente.
* Etapa 2 - Criar dados de endereço.
* Etapa 3 - Enviar uma foto.
* Etapa 4 - Detalhar os dados preenchidos, com opcao de aceitar ou recusar.
* Aceite - Cria-se uma conta e envia email agradecendo. No email ha um link para criar login e senha.
* Recusa - Envia email pedindo para aceitar a proposta. Caso aceite, segue o fluxo do aceite.
* Logar - Apos logar, pode-se transferir valor para outra conta.

As tecnologias basicas de todos são:

* SpringBoot
* Rest HATEOAS
* Java 11

## Observacoes

Nao foi pesquisado e pensado em otimizacoes de docker, bem como ambientes separados com spring cloud.
