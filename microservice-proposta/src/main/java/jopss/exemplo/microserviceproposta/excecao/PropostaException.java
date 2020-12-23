package jopss.exemplo.microserviceproposta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PropostaException extends RuntimeException {
    public PropostaException(String message) {
        super(message);
    }
    public PropostaException(Throwable cause) {
        super(cause);
    }
}
