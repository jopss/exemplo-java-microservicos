package jopss.exemplo.microserviceproposta.integracao.documento;

import jopss.exemplo.microserviceproposta.integracao.RequisicaoMicroservices;
import org.springframework.web.multipart.MultipartFile;

public class DocumentoRequisicao extends RequisicaoMicroservices {
    private Long idPessoa;
    private MultipartFile file;

    public DocumentoRequisicao(Long idPessoa, MultipartFile file) {
        this.idPessoa = idPessoa;
        this.file = file;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public MultipartFile getFile() {
        return file;
    }
}
