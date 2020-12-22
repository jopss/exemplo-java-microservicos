package jopss.exemplo.microservicecpf.rest;

import jopss.exemplo.microservicecpf.negocio.CPFService;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("cpf")
@Validated
public class CPFRest {

    @Autowired
    private CPFService service;

    @GetMapping(value = "{numero}/validar-tratar")
    public ResponseEntity validarETratar(@PathVariable @CPF @NotBlank String numero) {
        return new ResponseEntity<>(this.service.tratar(numero), HttpStatus.OK);
    }

}
