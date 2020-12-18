package jopss.exemplo.microserviceendereco.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EnderecoException extends RuntimeException {
    public EnderecoException(String message) {
        super(message);
    }
    public EnderecoException(Throwable cause) {
        super(cause);
    }
}
