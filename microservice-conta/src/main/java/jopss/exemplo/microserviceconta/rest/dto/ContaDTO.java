package jopss.exemplo.microserviceconta.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContaDTO {
    @NotBlank
    private String emailCliente;
    @NotNull
    private Long idCliente;
    @NotNull
    private Long idProposta;

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }
}
