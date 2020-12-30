package jopss.exemplo.microserviceconta.integracao.cliente;

import jopss.exemplo.microserviceconta.integracao.IntegracaoMicroservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class ClienteAdapter extends IntegracaoMicroservices<ClienteRequisicao, ClienteAPI> {

    @Autowired
    private ClienteClient client;

    protected ClienteAPI tratar(ClienteRequisicao cliente) {
        return this.client.buscar(cliente.getId());
    }
}
