package jopss.exemplo.microserviceproposta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PropostaInexistenteException extends RuntimeException {
    public PropostaInexistenteException(String message) {
        super(message);
    }
    public PropostaInexistenteException(Throwable cause) {
        super(cause);
    }
}
