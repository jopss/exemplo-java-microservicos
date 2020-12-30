package jopss.exemplo.microservicecliente.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteInexistenteException extends RuntimeException {
    public ClienteInexistenteException(String message) {
        super(message);
    }
    public ClienteInexistenteException(Throwable cause) {
        super(cause);
    }
}
