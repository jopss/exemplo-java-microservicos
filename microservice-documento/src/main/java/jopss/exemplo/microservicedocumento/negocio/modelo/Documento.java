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
    private String documento;

    public Documento() {
    }

    public Documento(@NotNull Long idPessoa, String documento) {
        this.idPessoa = idPessoa;
        this.documento = documento;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
