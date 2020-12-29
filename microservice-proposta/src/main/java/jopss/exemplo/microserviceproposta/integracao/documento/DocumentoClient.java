package jopss.exemplo.microserviceproposta.integracao.documento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "microservico-documento", url = "${microservico.documento}")
public interface DocumentoClient {

    @PostMapping(value = "pessoa/{idPessoa}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    EntityModel<DocumentoAPI> enviarDocumentoPessoa(@PathVariable("idPessoa") Long idPessoa, @RequestPart(name = "file") MultipartFile file);

}
