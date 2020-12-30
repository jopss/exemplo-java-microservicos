package jopss.exemplo.microserviceconta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class IntegracaoException extends RuntimeException {
    public IntegracaoException(String message) {
        super(message);
    }
    public IntegracaoException(Throwable cause) {
        super(cause);
    }
}
