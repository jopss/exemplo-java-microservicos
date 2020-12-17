package jopss.exemplo.microservicecpf.rest;

import jopss.exemplo.microservicecpf.rest.dto.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("cpf")
public class CPFRest {

    @GetMapping(value = "validar-tratar")
    public ResponseEntity validarETratar(@Valid CPF cpf) {
        return new ResponseEntity<>(cpf, HttpStatus.OK);
    }

}
