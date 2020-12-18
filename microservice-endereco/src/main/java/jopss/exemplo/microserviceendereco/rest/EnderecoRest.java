package jopss.exemplo.microserviceendereco.rest;

import jopss.exemplo.microserviceendereco.negocio.EnderecoService;
import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("endereco")
public class EnderecoRest {

    @Autowired
    private EnderecoService service;

    @GetMapping("buscar")
    public ResponseEntity buscarCep(@Valid String cep) {
        Endereco endereco = this.service.buscarCep(cep);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

}
