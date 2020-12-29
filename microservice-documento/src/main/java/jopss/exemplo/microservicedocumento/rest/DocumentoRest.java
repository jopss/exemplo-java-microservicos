package jopss.exemplo.microservicedocumento.rest;

import jopss.exemplo.microservicedocumento.negocio.DocumentoService;
import jopss.exemplo.microservicedocumento.negocio.modelo.Arquivo;
import jopss.exemplo.microservicedocumento.negocio.modelo.Documento;
import jopss.exemplo.microservicedocumento.rest.dto.DocumentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("documento")
@Validated
public class DocumentoRest {

    @Autowired
    private DocumentoService service;

    @PostMapping(value="/pessoa/{idPessoa}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity receberDocumento(@PathVariable @NotNull Long idPessoa, @RequestParam("file") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Documento documento = this.service.gravar(idPessoa, fileName, file.getInputStream());

        //TODO: daria para fazer mais rapido com spring-boot-starter-parent 2.3+
        DocumentoResponse resp = new DocumentoResponse(documento);
        resp.add(linkTo(methodOn(DocumentoRest.class).retornarDocumento(idPessoa)).withSelfRel());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping(value = "/pessoa/{idPessoa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retornarDocumento(@PathVariable @NotNull Long idPessoa) {
        Arquivo arquivo = this.service.retornarPorPessoa(idPessoa);
        return new ResponseEntity<>(arquivo, HttpStatus.OK);
    }
}
