package jopss.exemplo.microserviceproposta.integracao.endereco;

import jopss.exemplo.microserviceproposta.integracao.IntegracaoMicroservices;
import jopss.exemplo.microserviceproposta.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class EnderecoAdapter extends IntegracaoMicroservices<EnderecoAPI, Endereco> {

    @Autowired
    private EnderecoClient client;

    @Autowired
    private EnderecoConverter converter;

    protected Endereco tratar(EnderecoAPI requisicao) {
        EnderecoAPI api = this.client.buscarCep(requisicao.getCep());
        return this.converter.enderecoAPIParaModelo(api);
    }
}
