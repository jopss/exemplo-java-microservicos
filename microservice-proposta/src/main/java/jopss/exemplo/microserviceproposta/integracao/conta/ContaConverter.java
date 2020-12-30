package jopss.exemplo.microserviceproposta.integracao.conta;

import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import org.springframework.stereotype.Service;

@Service
public class ContaConverter {

    public ContaRequisicao modeloParaContaRequisicao(Proposta proposta){
        return new ContaRequisicao(proposta.getCliente().getEmail(), proposta.getIdCliente(), proposta.getId());
    }

}
