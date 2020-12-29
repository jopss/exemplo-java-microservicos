package jopss.exemplo.microserviceproposta.rest.dto;

import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import org.springframework.hateoas.RepresentationModel;

/**
 * Classe de resposta com proposta para adicionar links HATEOAS.
 */
public class PropostaResponse extends RepresentationModel<PropostaResponse> {

    private Proposta content;

    public PropostaResponse(Proposta proposta){
        this.content = proposta;
    }

    public Proposta getContent() {
        return content;
    }
}
