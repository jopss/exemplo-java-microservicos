package jopss.exemplo.microserviceconta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContaException extends RuntimeException {
    public ContaException(String message) {
        super(message);
    }
    public ContaException(Throwable cause) {
        super(cause);
    }
}
