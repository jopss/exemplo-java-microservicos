package jopss.exemplo.microservicecliente.rest;

import jopss.exemplo.microservicecliente.negocio.ClienteService;
import jopss.exemplo.microservicecliente.negocio.modelo.Cliente;
import jopss.exemplo.microservicecliente.rest.dto.ClienteDTO;
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
@RequestMapping("cliente")
@Validated
public class ClienteRest {

    @Autowired
    private ClienteService service;

    @PostMapping()
    public ResponseEntity cadastrar(@RequestBody @Valid ClienteDTO dto) {
        Cliente cliente = this.service.cadastrar(dto);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable @NotNull Long id) {
        Cliente cliente = this.service.buscarPorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

}
