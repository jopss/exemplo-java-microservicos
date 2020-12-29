package jopss.exemplo.microservicedocumento.rest.dto;

import jopss.exemplo.microservicedocumento.negocio.modelo.Documento;
import org.springframework.hateoas.RepresentationModel;

/**
 * Classe de resposta com documento para adicionar links HATEOAS.
 */
public class DocumentoResponse extends RepresentationModel<DocumentoResponse> {

    private Documento content;

    public DocumentoResponse(Documento documento){
        this.content = documento;
    }

    public Documento getContent() {
        return content;
    }
}
