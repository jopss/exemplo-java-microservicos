package jopss.exemplo.microservicedocumento.negocio.modelo;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Documento extends AbstractPersistable<Long> {

    @NotNull
    @Column(unique = true)
    private Long idPessoa;
    private String nomeDocumento;

    public Documento() {
    }

    public Documento(Long idPessoa, String nomeDocumento) {
        this.idPessoa = idPessoa;
        this.nomeDocumento = nomeDocumento;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomeDocumento() {
        return nomeDocumento;
    }

    public void setNomeDocumento(String nomeDocumento) {
        this.nomeDocumento = nomeDocumento;
    }
}
