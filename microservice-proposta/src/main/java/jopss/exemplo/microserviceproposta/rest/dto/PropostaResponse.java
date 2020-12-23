package jopss.exemplo.microserviceproposta.rest.dto;

import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import org.springframework.hateoas.RepresentationModel;

public class PropostaResponse extends RepresentationModel<PropostaResponse> {

    private Proposta proposta;

    public PropostaResponse(Proposta proposta){
        this.proposta = proposta;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
