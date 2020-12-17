package jopss.exemplo.microservicecpf.rest.dto;

import javax.validation.constraints.NotBlank;

public class CPF {

    @NotBlank
    @org.hibernate.validator.constraints.br.CPF
    private String documento;

    public CPF() {
    }

    public CPF(String documento) {
        this.documento = documento;
    }

    public String getDocumento() {
        return documento.replaceAll("\\.", "").replaceAll("-", "");
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
