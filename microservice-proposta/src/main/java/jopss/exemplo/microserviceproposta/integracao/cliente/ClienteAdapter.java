package jopss.exemplo.microserviceproposta.integracao.cliente;

import jopss.exemplo.microserviceproposta.integracao.IntegracaoMicroservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ClienteAdapter extends IntegracaoMicroservices<ClienteRequisicao, Long> {

    @Autowired
    private ClienteClient client;

    protected Long tratar(ClienteRequisicao requisicao) {
        ClienteAPI api = this.client.cadastrar(requisicao);
        return api.getId();
    }
}
