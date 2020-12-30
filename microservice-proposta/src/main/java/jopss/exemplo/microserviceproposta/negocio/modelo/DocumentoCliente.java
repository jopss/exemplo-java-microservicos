package jopss.exemplo.microserviceproposta.negocio.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class DocumentoCliente extends AbstractPersistable<Long> {
    @NotBlank
    private String nomeDocumento;
    @NotBlank
    private String arquivo;

    @JsonIgnore
    @OneToOne(mappedBy = "documento")
    private ClienteTemporario cliente;

    public DocumentoCliente(){}

    public DocumentoCliente(String nomeDocumento, String arquivo) {
        this.nomeDocumento = nomeDocumento;
        this.arquivo = arquivo;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public ClienteTemporario getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTemporario cliente) {
        this.cliente = cliente;
    }
}
