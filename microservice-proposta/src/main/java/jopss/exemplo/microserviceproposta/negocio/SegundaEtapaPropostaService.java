package jopss.exemplo.microserviceproposta.negocio;

import jopss.exemplo.microserviceproposta.integracao.endereco.EnderecoAPI;
import jopss.exemplo.microserviceproposta.integracao.endereco.EnderecoAdapter;
import jopss.exemplo.microserviceproposta.negocio.modelo.ClienteTemporario;
import jopss.exemplo.microserviceproposta.negocio.modelo.Endereco;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import jopss.exemplo.microserviceproposta.negocio.repository.ClienteTemporarioRepository;
import jopss.exemplo.microserviceproposta.negocio.repository.EnderecoRepository;
import jopss.exemplo.microserviceproposta.negocio.repository.PropostaRepository;
import jopss.exemplo.microserviceproposta.rest.dto.SegundaEtapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SegundaEtapaPropostaService {

    @Autowired
    private EnderecoAdapter enderecoAdapter;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteTemporarioRepository clienteTemporarioRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaService propostaService;

    @Transactional
    public Proposta cadastrar(String codigo, SegundaEtapa etapa) {
        Proposta proposta = this.propostaService.buscarProposta(codigo);
        this.tratarEndereco(proposta, etapa);
        return this.andarProposta(proposta);
    }

    private void tratarEndereco(Proposta proposta, SegundaEtapa etapa){
        Endereco endereco = this.enderecoAdapter.executar(new EnderecoAPI(etapa.getCep()));
        endereco = this.enderecoRepository.save(endereco);

        ClienteTemporario cliente = proposta.getCliente();
        cliente.setEndereco(endereco);
        cliente = this.clienteTemporarioRepository.save(cliente);

        proposta.setCliente(cliente);
        this.propostaRepository.save(proposta);
    }

    private Proposta andarProposta(Proposta proposta){
        proposta.andarSituacaoSegundaEtapa();
        return this.propostaRepository.save(proposta);
    }

}
