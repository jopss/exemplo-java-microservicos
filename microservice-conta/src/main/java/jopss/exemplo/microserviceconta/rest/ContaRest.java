package jopss.exemplo.microserviceconta.rest;

import jopss.exemplo.microserviceconta.negocio.ContaService;
import jopss.exemplo.microserviceconta.negocio.modelo.Conta;
import jopss.exemplo.microserviceconta.rest.dto.ContaDTO;
import jopss.exemplo.microserviceconta.rest.dto.ContaResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("conta")
@Validated
public class ContaRest {

    @Autowired
    private ContaService service;

    @PostMapping()
    public ResponseEntity cadastrar(@RequestBody @Valid ContaDTO dto) {
        Conta conta = this.service.cadastrar(dto);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity buscarPorNumero(@PathVariable @NotNull Integer numero) {
        ContaResposta resposta = this.service.buscarPorNumero(numero);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable @NotNull Long id) {
        ContaResposta resposta = this.service.buscarPorId(id);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

}
