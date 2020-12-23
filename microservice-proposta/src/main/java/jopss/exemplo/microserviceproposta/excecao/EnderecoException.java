package jopss.exemplo.microserviceproposta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EnderecoException extends RuntimeException {
    public EnderecoException(String message) {
        super(message);
    }
    public EnderecoException(Throwable cause) {
        super(cause);
    }
}
