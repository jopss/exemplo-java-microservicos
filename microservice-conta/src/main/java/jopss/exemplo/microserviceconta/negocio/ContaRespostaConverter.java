package jopss.exemplo.microserviceconta.negocio;

import jopss.exemplo.microserviceconta.integracao.cliente.ClienteAPI;
import jopss.exemplo.microserviceconta.integracao.proposta.PropostaAPI;
import jopss.exemplo.microserviceconta.negocio.modelo.Conta;
import jopss.exemplo.microserviceconta.rest.dto.ContaResposta;
import org.springframework.stereotype.Service;

@Service
public class ContaRespostaConverter {

    public ContaResposta contaModeloParaResposta(Conta conta, ClienteAPI cliente, PropostaAPI proposta) {
        return null;
    }
}
