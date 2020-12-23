package jopss.exemplo.microserviceproposta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CPFException extends RuntimeException {
    public CPFException(String message) {
        super(message);
    }
    public CPFException(Throwable cause) {
        super(cause);
    }
}
