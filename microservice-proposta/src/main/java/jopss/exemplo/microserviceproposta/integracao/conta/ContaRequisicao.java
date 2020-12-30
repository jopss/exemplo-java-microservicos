package jopss.exemplo.microserviceproposta.integracao.conta;

import jopss.exemplo.microserviceproposta.integracao.RequisicaoMicroservices;

public class ContaRequisicao extends RequisicaoMicroservices {
    private String emailCliente;
    private Long idCliente;
    private Long idProposta;

    public ContaRequisicao(String emailCliente, Long idCliente, Long idProposta) {
        this.emailCliente = emailCliente;
        this.idCliente = idCliente;
        this.idProposta = idProposta;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
