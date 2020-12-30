package jopss.exemplo.microserviceconta.integracao.cliente;

import jopss.exemplo.microserviceconta.integracao.RequisicaoMicroservices;

public class ClienteRequisicao extends RequisicaoMicroservices {
    private Long id;

    public ClienteRequisicao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
