package jopss.exemplo.microserviceproposta.rest;

import jopss.exemplo.microserviceproposta.negocio.PrimeiraEtapaPropostaService;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import jopss.exemplo.microserviceproposta.rest.dto.PrimeiraEtapa;
import jopss.exemplo.microserviceproposta.rest.dto.PropostaResponse;
import jopss.exemplo.microserviceproposta.rest.dto.SegundaEtapa;
import jopss.exemplo.microserviceproposta.rest.dto.TerceiraEtapa;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("proposta")
@Validated
public class PropostaRest {

    @Autowired
    private PrimeiraEtapaPropostaService service;

    @PostMapping("/primeira-etapa")
    public ResponseEntity cadastrarPrimeiraEtapa(@Valid @RequestBody PrimeiraEtapa etapa) {
        Proposta proposta = this.service.cadastrar(etapa);

        //obs: daria para fazer mais rapido com spring-boot-starter-parent 2.3+
        PropostaResponse resp = new PropostaResponse(proposta);
        resp.add(linkTo(methodOn(PropostaRest.class).buscarPorCodigo(proposta.getCodigo())).withSelfRel());
        resp.add(linkTo(methodOn(PropostaRest.class).cadastrarSegundaEtapa(null)).withRel("proximo"));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/segunda-etapa")
    public ResponseEntity cadastrarSegundaEtapa(@Valid SegundaEtapa etapa) {
        return null;
    }

    @PostMapping("/terceira-etapa")
    public ResponseEntity cadastrarTerceiraEtapa(@Valid TerceiraEtapa etapa) {
        return null;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable @Valid String codigo) {
        return null;
    }
}
