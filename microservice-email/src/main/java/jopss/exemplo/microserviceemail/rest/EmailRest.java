package jopss.exemplo.microserviceemail.rest;

import jopss.exemplo.microserviceemail.negocio.EmailService;
import jopss.exemplo.microserviceemail.rest.dto.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("email")
public class EmailRest {

    @Autowired
    private EmailService service;

    @PostMapping()
    public ResponseEntity enviarEmail(@Valid @RequestBody Email email) {
        this.service.enviarEmail(email);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }

}
