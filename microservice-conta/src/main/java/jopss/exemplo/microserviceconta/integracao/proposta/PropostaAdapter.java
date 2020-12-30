package jopss.exemplo.microserviceconta.integracao.proposta;

import jopss.exemplo.microserviceconta.integracao.IntegracaoMicroservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class PropostaAdapter extends IntegracaoMicroservices<PropostaRequisicao, PropostaAPI> {

    @Autowired
    private PropostaClient client;

    protected PropostaAPI tratar(PropostaRequisicao proposta) {
        return this.client.buscar(proposta.getId());
    }
}
