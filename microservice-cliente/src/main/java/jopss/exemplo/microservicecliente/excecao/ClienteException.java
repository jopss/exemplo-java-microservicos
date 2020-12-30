package jopss.exemplo.microservicecliente.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteException extends RuntimeException {
    public ClienteException(String message) {
        super(message);
    }
    public ClienteException(Throwable cause) {
        super(cause);
    }
}
