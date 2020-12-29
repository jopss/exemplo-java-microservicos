package jopss.exemplo.microserviceproposta.integracao.documento;

import jopss.exemplo.microserviceproposta.integracao.IntegracaoMicroservices;
import jopss.exemplo.microserviceproposta.negocio.modelo.DocumentoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

@Service
public final class DocumentoAdapter extends IntegracaoMicroservices<DocumentoRequisicao, DocumentoCliente> {

    @Autowired
    private DocumentoClient client;

    @Autowired
    private DocumentoConverter converter;

    protected DocumentoCliente tratar(DocumentoRequisicao requisicao) {
        EntityModel<DocumentoAPI> api = this.client.enviarDocumentoPessoa(requisicao.getIdPessoa(), requisicao.getFile());
        DocumentoAPI documento = api.getContent();
        String url = api.getRequiredLink("_self").getHref();
        return this.converter.documentoAPIParaModelo(documento, url);
    }
}
