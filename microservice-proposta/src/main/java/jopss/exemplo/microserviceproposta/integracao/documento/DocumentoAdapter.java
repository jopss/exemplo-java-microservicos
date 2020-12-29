package jopss.exemplo.microserviceproposta.integracao.documento;

import feign.FeignException;
import feign.RetryableException;
import jopss.exemplo.microserviceproposta.excecao.DocumentoException;
import jopss.exemplo.microserviceproposta.negocio.modelo.DocumentoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentoAdapter {

    @Autowired
    private DocumentoClient client;

    @Autowired
    private DocumentoConverter converter;

    public DocumentoCliente enviarDocumento(Long idPessoa, MultipartFile file) {
        try {
            EntityModel<DocumentoAPI> api = this.client.enviarDocumentoPessoa(idPessoa, file);
            DocumentoAPI documento = api.getContent();
            String url = api.getRequiredLink("_self").getHref();
            return this.converter.documentoAPIParaModelo(documento, url);
        }catch(FeignException.FeignClientException e){
            if(e.status() == 400){
                throw new DocumentoException("Documento invalido");
            }
            throw e;
        }catch(RetryableException e){
            throw new DocumentoException("Microservico Documento fora do ar. Tente novamente.");
        }
    }
}
