package jopss.exemplo.microserviceproposta.negocio.modelo;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class DocumentoCliente extends AbstractPersistable<Long> {
    @NotBlank
    private String documentoCliente;
    @NotBlank
    private String nomeArquivo;
    @OneToOne(mappedBy = "documento")
    private ClienteTemporario cliente;

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public ClienteTemporario getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTemporario cliente) {
        this.cliente = cliente;
    }
}
