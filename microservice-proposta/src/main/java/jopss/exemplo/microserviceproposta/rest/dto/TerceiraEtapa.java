package jopss.exemplo.microserviceproposta.rest.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class TerceiraEtapa {
    @NotBlank
    private String documento;
}
