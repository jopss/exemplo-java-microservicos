package jopss.exemplo.microserviceproposta.integracao.documento;

import jopss.exemplo.microserviceproposta.negocio.modelo.DocumentoCliente;
import org.springframework.stereotype.Service;

@Service
public class DocumentoConverter {

    public DocumentoCliente documentoAPIParaModelo(DocumentoAPI api, String url){
        DocumentoCliente documento = new DocumentoCliente(api.getNomeDocumento(), url);
        return documento;
    }

}
