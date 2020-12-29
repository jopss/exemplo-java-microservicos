package jopss.exemplo.microserviceproposta.integracao.cpf;

import jopss.exemplo.microserviceproposta.integracao.RequisicaoMicroservices;

public class CPFRequisicao extends RequisicaoMicroservices {
    private String cpf;

    public CPFRequisicao(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
