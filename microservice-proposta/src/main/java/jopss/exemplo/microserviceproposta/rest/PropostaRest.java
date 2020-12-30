package jopss.exemplo.microserviceproposta.rest;

import jopss.exemplo.microserviceproposta.negocio.PrimeiraEtapaPropostaService;
import jopss.exemplo.microserviceproposta.negocio.PropostaService;
import jopss.exemplo.microserviceproposta.negocio.SegundaEtapaPropostaService;
import jopss.exemplo.microserviceproposta.negocio.TerceiraEtapaPropostaService;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import jopss.exemplo.microserviceproposta.rest.dto.PrimeiraEtapa;
import jopss.exemplo.microserviceproposta.rest.dto.PropostaResponse;
import jopss.exemplo.microserviceproposta.rest.dto.SegundaEtapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("proposta")
@Validated
public class PropostaRest {

    @Autowired
    private PrimeiraEtapaPropostaService servicePrimeiraEtapa;

    @Autowired
    private SegundaEtapaPropostaService serviceSegundaEtapa;

    @Autowired
    private TerceiraEtapaPropostaService serviceTerceiraEtapa;

    @Autowired
    private PropostaService serviceProposta;

    @PostMapping("/primeira-etapa")
    public ResponseEntity cadastrarPrimeiraEtapa(@RequestBody @Valid PrimeiraEtapa etapa) {
        Proposta proposta = this.servicePrimeiraEtapa.cadastrar(etapa);

        //TODO: daria para fazer mais rapido com spring-boot-starter-parent 2.3+
        PropostaResponse resp = new PropostaResponse(proposta);
        resp.add(linkTo(methodOn(PropostaRest.class).buscarPorCodigo(proposta.getCodigo())).withSelfRel());
        resp.add(linkTo(methodOn(PropostaRest.class).cadastrarSegundaEtapa(proposta.getCodigo(),null)).withRel("proximo"));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/codigo/{codigo}/segunda-etapa")
    public ResponseEntity cadastrarSegundaEtapa(@PathVariable String codigo, @RequestBody @Valid SegundaEtapa etapa) {
        Proposta proposta = this.serviceSegundaEtapa.cadastrar(codigo, etapa);

        //TODO: daria para fazer mais rapido com spring-boot-starter-parent 2.3+
        PropostaResponse resp = new PropostaResponse(proposta);
        resp.add(linkTo(methodOn(PropostaRest.class).buscarPorCodigo(proposta.getCodigo())).withSelfRel());
        resp.add(linkTo(methodOn(PropostaRest.class).cadastrarTerceiraEtapa(proposta.getCodigo(),null)).withRel("proximo"));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/codigo/{codigo}/terceira-etapa")
    public ResponseEntity cadastrarTerceiraEtapa(@PathVariable String codigo, @RequestParam("file") MultipartFile file) {
        Proposta proposta = this.serviceTerceiraEtapa.cadastrar(codigo, file);

        //TODO: daria para fazer mais rapido com spring-boot-starter-parent 2.3+
        PropostaResponse resp = new PropostaResponse(proposta);
        resp.add(linkTo(methodOn(PropostaRest.class).detalhar(proposta.getCodigo())).withRel("detalhar"));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable @NotBlank String codigo) {
        Proposta proposta = this.serviceProposta.buscarProposta(codigo);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscar(@PathVariable @NotNull Long id) {
        Proposta proposta = this.serviceProposta.buscarPorId(id);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @GetMapping("/codigo/{codigo}/detalhar")
    public ResponseEntity detalhar(@PathVariable @NotBlank String codigo) {
        Proposta proposta = this.serviceProposta.detalhar(codigo);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @PostMapping("/codigo/{codigo}/aceitar")
    public ResponseEntity aceitarProposta(@PathVariable String codigo) {
        Proposta proposta = this.serviceProposta.aceitar(codigo);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @PostMapping("/codigo/{codigo}/recusar")
    public ResponseEntity recusarProposta(@PathVariable String codigo) {
        Proposta proposta = this.serviceProposta.recusar(codigo);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }
}
