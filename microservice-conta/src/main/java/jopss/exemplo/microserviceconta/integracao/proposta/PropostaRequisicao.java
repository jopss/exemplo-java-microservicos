package jopss.exemplo.microserviceconta.integracao.proposta;

import jopss.exemplo.microserviceconta.integracao.RequisicaoMicroservices;

public class PropostaRequisicao extends RequisicaoMicroservices {
    private Long id;

    public PropostaRequisicao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
