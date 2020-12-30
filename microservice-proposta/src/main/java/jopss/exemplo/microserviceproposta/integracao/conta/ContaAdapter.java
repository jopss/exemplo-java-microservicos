package jopss.exemplo.microserviceproposta.integracao.conta;

import jopss.exemplo.microserviceproposta.integracao.IntegracaoMicroservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ContaAdapter extends IntegracaoMicroservices<ContaRequisicao, Long> {

    @Autowired
    private ContaClient client;

    protected Long tratar(ContaRequisicao requisicao) {
        ContaAPI api = this.client.cadastrar(requisicao);
        return api.getId();
    }
}
