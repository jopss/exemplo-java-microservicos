package jopss.exemplo.microserviceproposta.integracao.cpf;

import jopss.exemplo.microserviceproposta.integracao.IntegracaoMicroservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class CPFAdapter extends IntegracaoMicroservices<CPFRequisicao, String> {

    @Autowired
    private CPFClient client;

    protected String tratar(CPFRequisicao requisicao) {
        return this.client.tratarEValidarCPF(requisicao.getCpf());
    }
}
