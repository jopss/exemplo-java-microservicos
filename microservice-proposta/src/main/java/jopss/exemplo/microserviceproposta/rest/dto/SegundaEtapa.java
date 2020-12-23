package jopss.exemplo.microserviceproposta.rest.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class SegundaEtapa {
    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    private String bairro;
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
}
