package jopss.exemplo.microserviceendereco.rest;

import jopss.exemplo.microserviceendereco.negocio.EnderecoService;
import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("endereco")
@Validated
public class EnderecoRest {

    @Autowired
    private EnderecoService service;

    @GetMapping("/{cep}")
    public ResponseEntity buscarCep(@PathVariable @NotBlank String cep) {
        Endereco endereco = this.service.buscarCep(cep);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

}
