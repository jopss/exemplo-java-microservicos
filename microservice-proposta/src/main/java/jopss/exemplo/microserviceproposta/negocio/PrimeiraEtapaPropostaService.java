package jopss.exemplo.microserviceproposta.negocio;

import jopss.exemplo.microserviceproposta.excecao.PropostaException;
import jopss.exemplo.microserviceproposta.integracao.cpf.CPFAdapter;
import jopss.exemplo.microserviceproposta.integracao.cpf.CPFRequisicao;
import jopss.exemplo.microserviceproposta.negocio.modelo.ClienteTemporario;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import jopss.exemplo.microserviceproposta.negocio.repository.ClienteTemporarioRepository;
import jopss.exemplo.microserviceproposta.negocio.repository.PropostaRepository;
import jopss.exemplo.microserviceproposta.rest.dto.PrimeiraEtapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrimeiraEtapaPropostaService {

    @Autowired
    private CPFAdapter cpfAdapter;

    @Autowired
    private ClienteTemporarioRepository clienteTemporarioRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Transactional
    public Proposta cadastrar(PrimeiraEtapa etapa) {
        this.tratarCPF(etapa);
        this.validarUnicidade(etapa);
        return this.gerarProposta(etapa);
    }

    private void validarUnicidade(PrimeiraEtapa etapa){
        int qtd = this.clienteTemporarioRepository.countClienteTemporarioByCpfOrEmail(etapa.getCpf(), etapa.getEmail());
        if(qtd > 0){
            throw new PropostaException("Ja existe uma proposta para este CPF ou Email");
        }
    }

    private void tratarCPF(PrimeiraEtapa etapa){
        String cpfValido = this.cpfAdapter.executar(new CPFRequisicao(etapa.getCpf()));
        etapa.setCpf(cpfValido);
    }

    private Proposta gerarProposta(PrimeiraEtapa etapa){
        ClienteTemporario clienteTemporario = this.gerarClienteTemporario(etapa);
        Proposta proposta = new Proposta(clienteTemporario);
        return this.propostaRepository.save(proposta);
    }

    private ClienteTemporario gerarClienteTemporario(PrimeiraEtapa etapa){
        ClienteTemporario cli = new ClienteTemporario(etapa.getNome(), etapa.getSobrenome(), etapa.getCpf(), etapa.getEmail(), etapa.getDataNascimento());
        return this.clienteTemporarioRepository.save(cli);
    }
}
